FROM envoyproxy/envoy:v1.34.0 AS builder
COPY envoy.yaml /etc/envoy/envoy.yaml
CMD ["/usr/local/bin/envoy", "-c", "/etc/envoy/envoy.yaml"]