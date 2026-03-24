FROM --platform=linux/amd64 ubuntu:22.04

WORKDIR /app

# Install required dependencies
RUN apt-get update && apt-get install -y --no-install-recommends \
    openjdk-17-jre-headless \
    libqt5core5a libqt5widgets5 libqt5gui5 \
    wget ca-certificates \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

COPY test_sprt.sh /app/test_sprt.sh
COPY engines /app/engines
COPY sprt_presets.ini /app/sprt_presets.ini
COPY 8moves_v3.pgn /app/8moves_v3.pgn
COPY cutechess-cli /app/cutechess-cli

RUN chmod +x /app/test_sprt.sh

ENTRYPOINT ["/app/test_sprt.sh"]
