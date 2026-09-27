#!/usr/bin/env -S uv run --script
# /// script
# requires-python = ">=3.11"
# dependencies = []
# ///
"""Run every benchmark and save each output.

    benchmarks/sll.1.txt   --sll   SinglyLinkedList
    benchmarks/sllt.1.txt  --sllt  SinglyLinkedListWithTail
    benchmarks/dll.1.txt   --dll   DoublyLinkedList
    benchmarks/dllt.1.txt  --dllt  DoublyLinkedListWithTail
    benchmarks/q.1.txt     --q     DynamicQueue
    benchmarks/s.1.txt     --s     DynamicStack

Usage:
    ./run_benches.py            # one iteration of each test
    ./run_benches.py 3          # three iterations of each test
    ./run_benches.py 3 8        # three iterations, max 8 concurrent jobs
"""

import os
import subprocess
import sys
from concurrent.futures import ThreadPoolExecutor, as_completed
from pathlib import Path

BENCHMARKS = ["sll", "sllt", "dll", "dllt", "q", "s"]

def run_one(launcher: Path, out_dir: Path, name: str, it: int, iterations: int) -> tuple[str, int, bool]:
    output = out_dir / f"{name}.{it}.txt"
    print(f"==> starting --{name} [{it}/{iterations}] -> {output}", file=sys.stderr)

    with output.open("wb") as f:
        proc = subprocess.run([str(launcher), f"--{name}"], stdout=f, stderr=subprocess.PIPE)

    if proc.returncode != 0:
        print(
            f"warning: --{name} [{it}/{iterations}] exited with status {proc.returncode}",
            file=sys.stderr,
        )
        return name, it, False

    if output.stat().st_size == 0:
        print(f"warning: --{name} [{it}/{iterations}] produced no data", file=sys.stderr)
        return name, it, False

    with output.open() as f:
        lines = sum(1 for _ in f)
    print(f"    done --{name} [{it}/{iterations}]: {lines} lines -> {output}", file=sys.stderr)
    return name, it, True


def main() -> None:
    script_dir = Path(__file__).resolve().parent
    os.chdir(script_dir)

    iterations = int(sys.argv[1]) if len(sys.argv) > 1 else 1
    max_parallel = int(sys.argv[2]) if len(sys.argv) > 2 else (os.cpu_count() or 4)

    out_dir = script_dir / "benchmarks"
    out_dir.mkdir(exist_ok=True)

    print("building...", file=sys.stderr)
    build = subprocess.run(["./gradlew", "-q", ":app:installDist"])
    if build.returncode != 0:
        print("error: build failed", file=sys.stderr)
        sys.exit(1)

    launcher = script_dir / "app" / "build" / "install" / "app" / "bin" / "app"
    jobs = [(name, it) for name in BENCHMARKS for it in range(1, iterations + 1)]

    print(f"running with max {max_parallel} concurrent jobs", file=sys.stderr)

    fail_list: list[str] = []
    with ThreadPoolExecutor(max_workers=max_parallel) as pool:
        futures = {
            pool.submit(run_one, launcher, out_dir, name, it, iterations): (name, it)
            for name, it in jobs
        }
        for future in as_completed(futures):
            name, it, ok = future.result()
            if not ok:
                fail_list.append(f"{name}.{it}")

    fail_path = out_dir / ".failed"
    fail_path.write_text("\n".join(fail_list) + ("\n" if fail_list else ""))

    if fail_list:
        print(f"done with warnings ({' '.join(fail_list)}). see {out_dir}/", file=sys.stderr)
    else:
        print(f"done. benchmark logs are in {out_dir}/", file=sys.stderr)


if __name__ == "__main__":
    main()
