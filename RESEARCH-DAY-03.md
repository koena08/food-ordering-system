# Day 03 Research

**Q1. What is JPA? What is Hibernate? How are they related?**
>JPA (Java Persistence API) is a specification; it is just a set of interfaces and rules defined by Java for mapping objects to relational databases. Hibernate is the actual framework that implements those rules. JPA is the blueprint and Hibernate is the building.

**Q2. What is the difference between @Entity and @Table?**
>`@Entity` tells Hibernate to treat the Java class as a database entity that needs to be tracked. `@Table` is optional and allows you to specify exact database-level details, such as changing the table name (e.g. from the default `Menu` to `menus`).

**Q3. What is a foreign key? What is @ManyToOne? Give 2 real-world examples.**
>A foreign key is a column in one database table that refers to the primary key of another table, creating a link between them. `@ManyToOne` is the JPA annotation representing this relationship where many records in the current table point to one record in the parent table.
* *Example 1:* Many `Employees` belong to one `Department`.
* *Example 2:* Many `Menus` belong to one `Category`.

**Q4. What does @JoinColumn(name = "category_id") do?**
>It explicitly tells Hibernate exactly what to name the foreign key column in your database table. Without it, Hibernate might generate an ugly default column name like `category_id_fk`.

**Q5. Why store price as BigDecimal and not double?**
>The `double` primitive uses floating-point math which is notoriously imprecise and can introduce rounding errors (e.g. `0.1 + 0.2 = 0.30000000000000004`). `BigDecimal` provides absolute precision for financial calculations to ensure money values are never corrupted.

**Q6. What does FetchType LAZY vs EAGER mean? What is the default for @ManyToOne?**
>`EAGER` fetching means Hibernate immediately runs a join query to load the parent entity the second you load the child entity. `LAZY` fetching means Hibernate creates a proxy and only queries the database for the parent entity if you explicitly call its getter method later in the code. The default for `@ManyToOne` is `EAGER`.

**Q7. What is the N+1 query problem?**
>This happens when you run one query to load a list of entities (the "1"), and then Hibernate runs an additional separate query for *every single item* in that list to fetch its related data (the "N"). It severely degrades application performance.

**Q8. What is dependency injection? Constructor injection vs field injection — which is preferred and why?**
>Dependency injection is a design pattern where an object receives its dependencies from an external framework (like Spring) rather than creating them itself. Constructor injection is preferred over field injection (`@Autowired` on a property) because it ensures the object is fully initialised and cannot exist in an invalid state, making it safer and easier to unit test.

**Q9. What does @RequiredArgsConstructor (Lombok) do?**
>It automatically generates a constructor that accepts arguments for all `private final` fields in your class. When combined with Spring, it creates an elegant way to handle constructor dependency injection without writing boilerplate code.

**Q10. What is the role of the SERVICE layer? Why must it be separate from the controller?**
>The service layer contains all your core business logic, calculations and database interactions. It must be separate from the controller so that the controller is strictly responsible for handling HTTP traffic (status codes, routing and JSON parsing) while the service handles the actual work.

**Q11. Why MUST you validate that categoryId exists before saving a menu?**
>If you try to save a menu linked to a `categoryId` that does not exist in the database, your database will throw a fatal `DataIntegrityViolationException` due to a foreign key constraint failure. Validating it first allows you to return a clean 404 error to the client instead of a 500 server crash.

**Q12. Difference between save() and saveAndFlush()?**
>`save()` tells Hibernate to schedule the insert/update for the database, but Hibernate might wait until the very end of the transaction to actually run the SQL. `saveAndFlush()` forces Hibernate to execute the SQL statement immediately and sync the state to the database on that exact line of code.

**Q13. Why write private mapper methods (entity <-> dto)?**
>Mapper methods cleanly separate your internal database structure from your external API structure. Entities contain sensitive database information and cyclic relationships that should never be exposed to a frontend client. DTOs (Data Transfer Objects) ensure you only transmit exactly what the client needs.