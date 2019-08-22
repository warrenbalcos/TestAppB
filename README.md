# Test App B

A test service app that listens to requests and runs a background task

once the task is finished, a broadcast with the result will be sent.

features:
- Echo Background Service
- Math Operation Background Service  

## Getting Started


### Configuration

The host and port of the Echo Rest Api

com.wfdb.testappb.config.Config.java

```
DEFAULT_HOST = "127.0.0.1";
DEFAULT_PORT = "8080";
```

### Building the project

building the binaries 

```
./gradlew build
```

Or

- import the project on android studio.
- build and install on an emulator or on a device

### Running Tests

```
./gradlew test
```

### Author

* **Warren Balcos** - *Initial work* - [warrenbalcos](https://github.com/warrenbalcos)

### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details