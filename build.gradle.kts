tasks.register("clean", Delete::class) {
    delete(buildDir)
}
