# Day 02 Research & Self-Quiz

## 2. Concepts to Research

**Q1. What is a Java generic type? Why is <T> useful?**
>Java generics allow classes, interfaces and methods to operate on parameters of various types while providing compile-time type safety. The `<T>` stands for "Type" and it is useful because it prevents `ClassCastExceptions`. It ensures you only put the correct type of object into a wrapper and the compiler enforces this rule before the application even runs.

**Q2. What does Lombok @Builder generate behind the scenes?**
>When you use `@Builder`, Lombok automatically generates a static inner builder class, a private all-arguments constructor and fluent setter methods for all your fields. It also generates a `build()` method that returns the fully instantiated object.

**Q3. What is the Builder design pattern? When to use it?**
>The Builder pattern is a creational design pattern used to construct complex objects step by step. You should use it when an object has many optional parameters or when you want to create an immutable object without writing a massive constructor with a dozen arguments.

**Q4. What is LocalDateTime? How is it different from Date?**
>`LocalDateTime` is part of the modern Java 8 Date/Time API. It is immutable, thread-safe and represents a date-time without a time zone. The older `java.util.Date` is considered legacy; it is mutable, not thread-safe and technically represents a specific instant in time (milliseconds since the epoch) rather than a human-readable local time.

**Q5. Why does a consistent response format matter to frontend developers?**
>It allows frontend teams to write a single reusable API interceptor. Because they always know exactly where to find the payload (`data`), the status (`statusCode`) and any error text (`message`), they do not have to write custom parsing logic for every single endpoint.

**Q6. What does @JsonInclude(JsonInclude.Include.NON_NULL) do?**
>This annotation tells the Jackson JSON serializer to completely omit any fields from the final JSON response if their value is `null`. This keeps the API payloads smaller and cleaner (e.g. hiding the `data` field entirely on a DELETE request).

**Q7. What is a static factory method? Why use Response.success(...) instead of new Response<>()?**
>A static factory method is a public static method that returns an instance of the class. Using `Response.success()` has a clear, descriptive name that explains the exact intent of the object being created. It also hides the boilerplate logic (like setting the 200 status code and the current timestamp) from the developer writing the controller.

---

## 9. Self-Quiz

**Q1. Why use generic <T> instead of Object for data field?**
>If you use `Object`, the frontend or the service layer has to manually cast the response back to the specific type it expects which can lead to runtime crashes. Using `<T>` enforces the exact object type (like `CategoryDto`) at compile time.

**Q2. Difference between Response<T> and ResponseEntity<T>? Can you have both at once?**
>`Response<T>` is our custom Java class that defines the JSON body payload. `ResponseEntity<T>` is a built-in Spring Boot class that represents the entire HTTP response including the body, headers and network status code. Yes, you can absolutely use both at once which is why we nest them: `ResponseEntity<Response<CategoryDto>>`.

**Q3. If a request fails, what statusCode does Response hold?**
>It holds the specific HTTP status code passed into our `Response.error(code, message)` method, such as 400 for a validation error or 404 for a missing ID.

**Q4. Why add a timestamp?**
>Timestamps are critical for debugging and system logging. If a client experiences a crash or reports an issue, the timestamp allows backend developers to cross-reference the exact moment the payload was generated with the server logs to find the root cause.