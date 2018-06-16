[[dlp]]
= DLP
How to run this project: run the main in DlpApplication

This project uses `commons-validator` to help the validation. Extra work is done before `commons-validator` is used for final validation.
This web application is based on `spring-webflux`.

This project includes three apis to detect if the target includes sensitive data.


[[dlp-text]]
== Text

[source,java,intent=0]
[subs="verbatim,quotes"]
----
Url: http://localhost:8080/detect/text
Method: Post
Body: "My credit card number is 4012 8888 8888 1881"
----


[[dlp-file]]
== File

[source,java,intent=0]
[subs="verbatim,quotes"]
----
Url: http://localhost:8080/detect/file
Method: Post
Body: "classpath:/static/file.txt" or "file:c:\path\file.txt"
----


[[dlp-drive]]
== Drive
A public google doc (https://docs.google.com/document/d/1dJWb55zHCngbVDNcWrpcHFGG-fC3jHyVpW04ET65_U4/edit?usp=sharing) can be used for testing, feel free to change the content.
[source,java,intent=0]
[subs="verbatim,quotes"]
----
Url: http://localhost:8080/detect/drive?fileId=1dJWb55zHCngbVDNcWrpcHFGG-fC3jHyVpW04ET65_U4
Method: Get
----


