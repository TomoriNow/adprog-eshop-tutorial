# Tutorial Reflections
## Name: Muhammad Sean Arsha Galant
## NPM: 2206822963

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
to maintain reliability and verify our program further. 

However, having a 100% code coverage does not mean our code has no bugs or errors. Code coverage means what percentage of your codebase is covered by the tests or being tested. If you have no tests, then the code coverage is zero.
Thus, although we may have a 100% code coverage by tests in our code, it doesn't necessarily address the quality or correctness of the tests themselves. For instance, There might still be logical errors, edge cases, or other issues 
that are not adequately covered by the existing test suite that we have integrated. So a code might pass all of its unit tests, but it may still be prone to errors and bugs.

2. Creating a new functional test suite that verifies the number of items in the product list with the same setup procedures and instance variables can be a reasonable approach to maintain consistency and readability in the codebase.
Reusing setup procedures and instance variables could also ensure a standardized testing environment as it promotes maintainability in our code. Thus, if the new functional test suite follows the same conventions, naming conventions, 
and practices to the previous test suite (`CreateProductFunctionalTest.java`), it may increase the code quality. However, if the new test suite has new inconsistent information or deviations with the previous suite (such as not being 
cohesive and not maintaining a uniform structure in the code), it could reduce code quality. 

Consequently, this could lead to several clean code issues. One potential issue could be code duplication if the new class reproduces identical setup procedures and instance variables as the existing test suites. To address this, 
we can abstract common setup procedures into utility methods (Utility methods perform common functions, often reused and don't require to have object-level state, and tend to be global functions) or a base test class to promote 
code reuse and reduce duplication. This approach improves maintainability by centralizing setup logic and ensures consistency across test suites. Another issue may be Inconsistent naming or formatting which can make the code harder to 
understand and navigate. Thus, applying coding standards, conventions, and guidelines within the development team can help address these issues and promote clean code practices. The third potential issue could be the lack of independence
in the new test suite; if a test case's logic relies on the state or outcome of another test, it can introduce dependencies and make test maintenance more challenging. To mitigate this, isolate each test case to operate independently, 
utilizing setup and teardown methods to establish a clean state before each test execution.