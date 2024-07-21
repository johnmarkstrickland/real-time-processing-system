#!/bin/bash

# Wait for Kafka to be ready
curl -o wait-for-it.sh https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh
chmod +x wait-for-it.sh
/wait-for-it.sh kafka:9092 -t 60 --strict -- echo "Kafka is up"

# Create topics
#kafka-topics.sh --create --bootstrap-server kafka:9092 --replication-factor 1 --partitions 1 --topic OrderPlaced
kafka-topics.sh --create --topic OrderPlaced --bootstrap-server kafka:9092 --replication-factor 1 --partitions 1
kafka-topics.sh --create --topic InventoryUpdated --bootstrap-server kafka:9092 --replication-factor 1 --partitions 1
kafka-topics.sh --create --topic NotificationSent --bootstrap-server kafka:9092 --replication-factor 1 --partitions 1