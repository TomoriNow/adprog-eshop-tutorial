# Reflection
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