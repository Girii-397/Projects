# Performance Benchmarks and Testing Protocols

## Benchmarks
- **API Response Time**: <200ms for 95% of requests, including triage and chatbot.
- **AI Inference**: <1s per prediction; <500ms for triage, <2s for chatbot responses.
- **Database Queries**: <100ms for complex joins; <50ms for pharmacy inventory checks.
- **Scalability**: Handle 10,000 concurrent users, with emergency spikes up to 20,000.

## Testing Protocols
- **Unit Tests**: JUnit for services.
- **Integration Tests**: TestContainers for microservices.
- **AI Validation**: Accuracy >90%, fairness audits.
- **Load Testing**: JMeter for stress tests.
- **UAT**: User acceptance with real scenarios.