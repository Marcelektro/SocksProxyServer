# Socks Proxy Server

A super simple SOCKS proxy server application using [java-socks-proxy-server](https://github.com/bbottema/java-socks-proxy-server).


## Usage

Available environment variables:
- `PORT=<integer>` <- The port the proxy server will listen on
- `AUTH_USERNAME=<str>` <- Empty for no auth, or auth username
- `AUTH_PASSWORD=<str>` <- Empty for no auth, or auth password
- `DEBUG=<true>` <- Enable debug logging

To run the built program, simply run the SocksProxyServer jar file:
```bash
java -jar SocksProxyServer.jar
```


## Credits

- [java-socks-proxy-server](https://github.com/bbottema/java-socks-proxy-server) by [bbottema](https://github.com/bbottema)
