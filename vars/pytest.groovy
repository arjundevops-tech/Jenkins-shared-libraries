def call() {
    echo "Running pytest..."
    sh 'pytest -v --maxfail=1 --disable-warnings'
}
