# Tutorial Reflections
## Name: Muhammad Sean Arsha Galant
## NPM: 2206822963
## Link: https://adprog-eshop-tomorinow.koyeb.app/product/list
__________________________________________

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

__________________________________________

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

__________________________________________

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

__________________________________________

# Module 4 
## Reflection

1. Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests.

* Do I have enough functional tests to reassure myself that my application really works, from the point of view of the user?

No, I do not have enough functional tests because currently, other than the Product model that has these, I have not implemented functional tests for either the Car model or the payment model. Therefore, I must need to create more functional tests for both of these models so that I can reassure that the application really works well from the point of view from the user. Furthermore, I need to make a controller layer for the Payment model
to even begin creating functional tests so that the implementation of the newly added model could be tested from the user's point of view. Therefore, it is required that I create the controller for Payment, as well as the HTML template for the Payment model using Thyme so that I can test it functionally.

* Am I testing all the edge cases thoroughly?

Yes, I do believe have tested as many edge cases as possible thoroughly for each of the features and sub-features for the Payment model that includes tests for the Payment model, Payment repository, and Payment service/service implementation. In each of these test classes, I attempted to cover cases that include if each model/repository/service is empty or does not exist, setting the status of each of the classes (SUCCESS or REJECTED), and testing 
if for a certain wanted/successful test case (Happy path) or unwanted/malicious test case (or an UnhappyPath) the proposed test still leads to the expected result. Furthermore, I also attempted to try to cover all edge cases for the sub features that include the add Payment by voucher code and add Payment by cash-on-delivery. To do this, I tested both the Unhappy and Happy path cases for the voucher code (such as inputs more or less than 16 characters long, not starting with
"ESHOP" string, more or less than 8 numerical characters, etc.) and the same thing as well for the cash-on-delivery sub feature (such as if the address or deliveryFee parameters were empty or null).

* Do I have tests that check whether all my components fit together properly? Could some integrated tests do this, or are functional tests enough?

Yes, I do have tests to check whether all my components fit together properly through unit tests, however, I do lack functional and integration tests to ensure the compatibility between components. Nevertheless, The unit tests I integrated do incorporate the features of nearly all the layers (Model, Repository, Service/ServiceImplementation) except Controller (because I have not made the Controller layer for the Bonus). In the Repository Layer for both OrderRepository and PaymentRepository,
I created tests that incorporated the Order and Payment models to see if they would work with my implementation; such as creating new Order and Payment models, storing them into the repository, calling findById() or findAllPayments()/findAllByAuthor() on the models, etc. In the Service layer, I incorporated both the model and repository made previously in the tests so that I could test the compatibility of the service thoroughly, including edge cases and Unhappy paths stated previously.

* Are my tests giving me the confidence to refactor my code, fearlessly and frequently?

Yes, my tests do give me the confidence to refactor my code fearlessly and frequently as most of the time that I do finish my creating my tests and try running them through the skeleton or initial implementation of the classes I made, the tests would usually fail and satisfy the [RED] condition in the TDD workflow. This encouraged me to rethink and change parts of the program that I deem necessary to satisfy my tests and improve the implementation according to the specifications given 
in the assignment document. It is also noteworthy that my tests give me a good idea on how to refactor my code and how to use it step-by-step, although it requires a good amount of imagination to test about code that currently does not exist yet while creating my tests. Nevertheless, I always refactored it anyway according to the needs of my tests and the overall structure of the program.

* Are my tests helping me to drive out a good design? If I have a lot of integration tests but less unit tests, do I need to make more unit tests to get better feedback on my code design?

Yes, my tests are helping me out to drive out a good design of my program because it pushes me to think about the little details and functionalities that are required in each of the classes. For example, creating tests for the Service layer pushed me to think about how to differentiate between the type of payments that are going to be used by the user in a use case (either voucherCode or cash-on-delivery), which made me think about creating two separate HashMaps that test each type of payment.
Furthermore, this lead to me thinking about implementing two separate functions later on the service layer so that every a certain method is requested by the user for payment, I can use a switch case in Java (or if-statement) to call a function which checks the validity of each payment and sets its status accordingly. At the moment, I have a lot of unit tests but lack in integration tests, so I try to test out all the possible cases and edge cases for my code using unit tests; I tested 
how the code will perform under an Unhappy path and Happy path to make it balanced, and also test if the instantiation of classes could work accordingly in the tests.

* Are my feedback cycles as fast as I would like them? When do I get warned about bugs, and is there any practical way to make that happen sooner?

Yes, my feedback cycles are quite fast. They are definitely helpful for me since I get warned about bugs either from my run terminal in my IntelliJ IDE or from my CI/CD workflows on GitHub that I implemented in the previous module. Every time I push to GitHub during the [RED] commits, the workflows immediately tell me what went wrong as well as create a report using the PMD workflows that I check to improve and refactor my code even further. However, the idea of practically making the warning for bugs 
happen sooner though is by isolating the test into a single area/scope, or my running a subset of the full test suite so that the runtime of the tests are reduced. Making more meaningful/shorter tests that test out the correctness of the code could also make this happen since it reduces the lines of code that needs to be read by the compiler, and therefore speeds up the warnings/bugs.

* Is there some way that I could write faster integration tests that would get me feedback quicker?

Currently, No. It is impossible to write faster integration tests because at the moment I do not have any integration tests implemented in my project. Nevertheless, If I were to try to attempt to make/write faster integration tests, I would try to isolate each test more and make them more specific and specialized. This could speed up the tests and help me get feedback quicker. Furthermore, I feel like I could improve the setUp() function before each test so that running the integration tests would be quicker
and thus help me get quicker responses for warnings/bugs from the program. 

* Can I run a subset of the full test suite when I need to?

Yes, I can run a subset of the full test suite when needed. This can be possible since there are separate tests made for the model, repository, and service layers for the Order and Payment (and Car and Product) models respectively. So If I needed to run, for example, the repository tests only, then I can just set to run those tests while other tests are not ran in the test suite. This essentially isolates the amount of tests I need to run and improves the number of desired feedbacks and fixes I need to make for
the code.

* Am I spending too much time waiting for tests to run, and thus less time in a productive flow state?

Although I have created the mocks and stubs in the test so that in the case that the test fails I would quickly infer where to fix the problem, I do find myself sometimes being too absorbed into making the tests rather than implementing the actual program itself. This somewhat hinders my workflow, however, I still am able to run all my tests quickly regardless each time by running eshop_tests to get back on track (which only takes a couple of seconds to run). To improve upon this, I should create tests that are
less complex but still covers the program coverage well enough so that I can stay highly productive. Furthermore, I should think of creating more automated CI/CD workflows so that the tests could just be run automatically, and thus I wouldn't have wasted my time running the tests over and over again manually.

2. You have created unit tests in Tutorial. Now reflect whether your tests have successfully followed F.I.R.S.T. principle or not. If not, explain things that you need to do the next time you create more tests.

* Fast: Yes, running all tests only takes a couple seconds (2-3 seconds), thanks to effective use of mocking, stubbing, and the deliberate design focusing on testing specific methods, models, HashMaps, and other aspects individually. The tests were made quite isolated from one another, so it was quite quick to run as well without disrupting workflows.
* Isolated/Independent: Yes, each test operates independently, free from dependencies on external data or changes from other tests as they are well-isolated in each layer of the project (model, repository, service). Test states are defined within each test using mocking and doReturn from Mockito, with setUp reinitialized before every test (using BeforeEach).
* Repeatable: Yes, the tests could be run repeatedly and consistently as every time the test suite runs. The created tests also are unaffected by external services (such as external databases or programs), and the isolation of each test in their own layers also prevent them from interfering with other test cases. 
* Self-Validating: Yes, strict and correct assertions have been implemented into the test, testing every possible edge case or outcome using assertions such as assertEquals(), assertNull(), assertTrue(), assertFalse(), and assertNotNull() that test the correctness and state of each test to pass/fail. These assertions are included to throw errors, providing clear insight into the cause of any test failures. Furthermore, I used th verify() method from the Mockito test suite to test if a method is called a specific number of times using times(), and verify the absence of a test if a method is not called if it leads to an unhappy path. 
* Thorough/Timely: Yes, all possible happy and unhappy paths have been considered in the test, such as testing if a payment is successful/rejected, testing if it is empty or present, testing if it is null or not, and testing if the specific criteria (such as the criteria for voucherCode) are met. These comprehensive range of scenarios are created prior to implementation. Additionally, the tests also try to set valid and invalid statuses, as well as different order creation scenarios that could affect each creation of a payment.