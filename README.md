# excel-streaming-reader-sample
Sample demonstrating the use of [excel-streaming-reader](https://github.com/pjfanning/excel-streaming-reader) when reading xlsx files.

The sample uses `setUseSstTempFile(true)` and `setEncryptSstTempFile(true)` to ensure the Shared Strings data is not kept in memory.

`./gradlew clean run`

