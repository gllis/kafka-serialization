Apache Kafka serializers and deserializers

## usage
```java
public class Test {
    @Test
    public void test() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.1.207:9092,192.168.1.208:9092,192.168.1.209:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.gllis.kafka.serialization.KryoSerializer");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "com.gllis.kafka.serialization.KryoDeserializer");
        properties.put("auto.offset.reset","earliest");
        properties.put("group.id", "test");

        KafkaProducer<String, User> kafkaProducer = new KafkaProducer<>(properties);
        for (int i = 1; i <= 10; i++) {
            //参数1：topic名, 参数2：消息文本； ProducerRecord多个重载的构造方法
            User user = new User(i, "Name" +i);
            kafkaProducer.send(new ProducerRecord<>("user.info", user));
            System.out.println("message" + i);
        }

        kafkaProducer.close();

        KafkaConsumer<String, User> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList("user.info"));
        try {
            while (true) {
                //拉取消息
                ConsumerRecords<String, User> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, User> record : records) {
                    System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), JSON.toJSONString(record.value()));
                }
            }
        } finally {
            consumer.close();
        }
   }
}
```