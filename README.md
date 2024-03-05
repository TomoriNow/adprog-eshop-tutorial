# Tutorial Reflections
## Name: Muhammad Sean Arsha Galant
## NPM: 2206822963

# Module 1
## Reflection 1 | Exercise 1

In this first module, I have learned several coding standards that are useful for the first exercise. The coding standards could be summarized into 4 main parts which
include Clean Code (creating meaningful names, functions, comments, making use of objects and data structure, and error handling), Git flow (centralized/trunk-based workflow,
feature branch workflow, other workflows such as Gitflow), as well as Secure coding. For the 2 new features implemented so far (Edit and Delete product), some clean code and secure coding
principles have been applied. Examples of clean code principles in the source code are using well-defined and meaningful names for variables and functions (such as productId attribute to 
identify the id in the Product model or the getProductId() function) and making use of Objects and Data structure in the code like using the Product model in multiple instances of the code.

On the other hand, the Secure Coding practices include Output Data Encoding by paying attention to persistent free-form user input that comes from the Edit product feature and Authentication
by avoiding storing passwords in code or in configuration files. I also utilized the Git flow techniques when adding, committing, pushing and merging branches in the code from the edit-product
and delete-product branch to the main branch. However, some improvements to the code can be made. For instance, it would be better if error handling was further used in throughout the code (try-except) because as of right now there is little
to no error handling in functions or other code. Furthermore, the features and the application as of right now lacks any form of authentication and authorization for segregating different users, so 
in the future it would be good to add these Secure Coding practices to make the code more clean. Input validation needs to also be added to the input fields of the code, especially for the Create Product
and Edit Product pages because they both have string and numerical fields.

## Reflection 2 | Exercise 2
1. After writing the unit tests, I feel more confident that my code can run well for the newly added features (Edit and Delete). It is as though my code has become more reliable after the making unit tests 
and seeing it pass those tests. I believe that the number of unit tests that should be made in a class depends on the number of features or the complexity of the class itself. However, it is a good rule of thumb
to cover various scenarios, all features in the class, typical or atypical inputs, as well as potential error conditions (types of inputs that could result in Errors and Exceptions). The aim is to ensure a comprehensive 
test coverage that validates the class's behavior under different circumstances. To make sure that our unit tests are enough to verify our program, we can utilize code coverage tools to measure the scope or percentage of
our code that has been covered by our tests. We can also focus on creating meaningful test cases that address specific functionalities and potential points of failure, as well as regularly reviewing and updating unit tests
to maintain reliability and verify our program further. However, having a 100% code coverage does not mean our code has no bugs or errors. Code coverage means what percentage of your codebase is covered by the tests or being tested. If you have no tests, then the code coverage is zero.
Thus, although we may have a 100% code coverage by tests in our code, it doesn't necessarily address the quality or correctness of the tests themselves. For instance, There might still be logical errors, edge cases, or other issues 
that are not adequately covered by the existing test suite that we have integrated. So a code might pass all of its unit tests, but it may still be prone to errors and bugs.

2. Creating a new functional test suite that verifies the number of items in the product list with the same setup procedures and instance variables can be a reasonable approach to maintain consistency and readability in the codebase.
Reusing setup procedures and instance variables could also ensure a standardized testing environment as it promotes maintainability in our code. Thus, if the new functional test suite follows the same conventions, naming conventions, 
and practices to the previous test suite (`CreateProductFunctionalTest.java`), it may increase the code quality. However, if the new test suite has new inconsistent information or deviations with the previous suite (such as not being 
cohesive and not maintaining a uniform structure in the code), it could reduce code quality. Consequently, this could lead to several clean code issues. One potential issue could be code duplication if the new class reproduces identical setup procedures and instance variables as the existing test suites. To address this, 
we can abstract common setup procedures into utility methods (Utility methods perform common functions, often reused and don't require to have object-level state, and tend to be global functions) or a base test class to promote 
code reuse and reduce duplication. This approach improves maintainability by centralizing setup logic and ensures consistency across test suites. Another issue may be Inconsistent naming or formatting which can make the code harder to 
understand and navigate. Thus, applying coding standards, conventions, and guidelines within the development team can help address these issues and promote clean code practices. The third potential issue could be the lack of independence
in the new test suite; if a test case's logic relies on the state or outcome of another test, it can introduce dependencies and make test maintenance more challenging. To mitigate this, isolate each test case to operate independently, 
utilizing setup and teardown methods to establish a clean state before each test execution.


# Module 2
## Reflection 4.2

1. The code quality issues that were fixed included fixing the imports from `ProductController.java` as well as removing the unnecessary `public` modifiers from `ProductService.java`. To carry out fixing these code quality issues, 
I set up and used the PMD code scanning tool and added it to my GitHub Workflows. After setting up the PMD tool locally in a `pmd.yml` file, I pushed it to my online GitHub to initiate the workflow. I then checked the most recent commit
and downloaded the report generated by PMD from the GitHub Actions tab to identify the issues. In the report I found 2 issues that included import issues from `ProductController.java` and unnecessary `public` modifiers from `ProductService.java`. I started fixing 
the first issue in `ProductController.java`, because the line `import org.springframework.web.bind.annotation.*;` included ALL imports from `org.springframework.web.bind.annotation`, while I only used several of them. Thus, I removed this line
and then added all the necessary imports separately for `RequestMapping`, `GetMapping`, `PostMapping`, `ModelAttribute`, and `PathVariable` to fix the code quality issue. I added and commited this change and pushed it to my online GitHub repository.
I downloaded the PMD report once more on the most recent commit and observed that the code quality issue has been addressed. Afterward, I moved on to fixing the second code quality issue in `ProductService.java`. I removed all `public` modifiers from
all methods in the file because the `ProductService` interface was already public, so adding `public` modifiers to each method there is redundant and reduces the code quality. After removing all the `public` modifiers, I added and commited this change,
then pushed it to my online GitHub repository. I downloaded the PMD report once more from the most recent commit, and observed that the code quality issues I fixed earlier has all been addressed.

2. By definition, Continuous Integration (CI) is a software development practice where continuous changes & updates in codebase are integrated and verified by an automated build script using various tools, and Continuous Deployment (CD) is a software
deployment practice where its role is to automatically deploy the code to the application folder on the specified server. After observing the current CI/CD workflows on GitHub, I believe the current implementation has already met both the definitions of
CI and CD because firstly, there is already a CI script that is running on the repository where it automatically runs tests and scripts in order to check if the code runs correctly. This is added with other automatic code scanning tools which are PMD
and the Scorecard, which run further tests on the code to see what is wrong. CD is also satisfied in this case because I have registered the repository to Koyeb, and each push/commit made to the repository is automatically ran for deployment or redeployment
on Koyeb continuously. Due to all of these automated processes described, it could be concluded that the GitHub workflows does indeed satisfy the CI/CD requirements thus far.

# Module 3
## Reflection

1. The SOLID principles that I applied to my project were the Single Responsibility Principle (SRP), Open-Close Principle (OCP), Liskov Substitution Principle (LSP), and Dependency Inversion Principle (DIP). The Single Responsibility Principle is defined as where a class should have only one reason to change, 
meaning that it should have only one responsibility or job. SRP can be seen in the `Item` model which is an abstract class where we ensure common attributes (id, price, quantity) are centralized, and subclasses `Car` and `Product` can inherit them while also having their own single responsibilities/attributes.
The Liskov Substitution Principle states that Subtypes must be substitutable for their base types without altering the correctness of the program, meaning that if a class is a subtype of another class, it should be usable wherever the base class is used. LSP is seen in the program as both the `ProductServiceImpl` and `CarServiceImpl` are modified to use the
`ProductRepositoryInterface` and `CarRepositoryInterface` interfaces so that the repository interface for each item is substitutable while not breaking overall functionality. The Open-Closed Principle is where Software entities (classes, modules, functions, etc.) should be open for 
extension but closed for modification. OCP is seen in the project when we create a product in `ProductServiceImpl`, we extend and provide the product with a UUID if it is null or has no value, not modify the software artifact directly by setting a default UUID to the product. The Dependency Inversion Principle is stated as the principle where High-level modules should not depend on low-level modules. 
Both should depend on abstractions, and abstractions should not depend on details, while details should depend on abstractions/abstract classes. DIP can be seen in the program where both the `ProductRepository` and `CarRepository` are made to depend on their own relevant interfaces, that is `ProductRepositoryInterface` and `CarRepositoryInterface`.

2. The advantages of applying the SOLID principles to my project include minimizing dependencies to avoid ripple affects in other parts of the program, adding extensibility, and enhancing maintainability. We can observe less dependency in the project since we have the `ProductRepositoryInterface` and `CarRepositoryInterface` that is utilized 
for the `ProductServiceImpl` and `CarServiceImpl` classes, rather than concrete implementations. This reduces the direct dependency on a specific implementation and allows for flexibility in choosing or extending different repository implementations. This correlates next with adding extensibility, where we can observe that both the `Car` and `Product`
classes extend the `Item` class. Therefore, if developers wish to add new products in the future, they can just extend the `Item` class without any further modification to the existing code. Finally, maintainability is enhanced in the program through the new interfaces previously discussed. Any changes to data access or repository-related functionality 
can be made in the `ProductRepositoryInterface` and `CarRepositoryInterface` or its implementations without affecting the business logic in `ProductServiceImpl` and `CarServiceImpl`. This separation makes the codebase more modular, and changes in one area (e.g., data access) are less likely to impact other areas.

3. The disadvantages of not applying SOLID to my project is that it reduces maintainability, extensibility, and increases the likelihood of ripple affects taking place in other parts of the program if one part is changed. For example, if the newly introduced interfaces were not used, it is necessary to directly modify the business logic in `ProductServiceImpl` and `CarServiceImpl`
if we were to make any changes or further develop the code in the future. This action itself lacks the ability for new developers to extend the program and maintain the correctness of the program in the future if unwanted modifications are made without the abstraction of an interface. Furthermore, if SOLID was not applied to the abstraction of interfaces and models in the program,
the project may have tangled dependencies and responsibilities, making it harder to understand. This complexity can lead to difficulties in debugging and maintaining the code. It also makes the code less modular and could also reduce the reusability of components, as they may be closely tied to specific implementations and contexts. For instance, we cannot reuse the newly introduced `Item` model
for future products that we may wish to add to the project, and could end up making and repeating more attributes for each product; this increases complexity and makes maintainability for each product harder to achieve in the future.

# Module 4 
## Reflection
