# compose2terraform
**Transforms your docker-compose file to a Terraform config file!**

# Introduction
Model Driven Engineering (MDE) is a software development approach
that focuses on creating and exploiting domain models as the core artifacts of software development. It is based on the idea of separating the development process
into two distinct parts : the design of the model and the generation of the software
from the model. The goal of MDE is to create a model of the system that can be
used to drive the development process, allowing for faster development cycles and
improved quality.

# Context of the project

## 1-Compose file 
Compose file is a YAML file that defines services, networks and volumes
for a Docker application. It is used to configure an application’s services, making
it easier to manage the application’s containers. Compose file contains all the information needed to run an application, including the application’s dependencies,
services, networks and volumes.

## 2-Terraform Configuration Language file
The Terraform Configuration Language (HCL - HashiCorp Configuration Language) file 
is a domain-specific language used by Terraform, an infrastructure as code (IaC) tool. 
Terraform allows users to define and provision infrastructure resources in a declarative manner.

## 3-Problematic
The process of transforming a Docker compose file to a Terraform config file manually is a labor-intensive task that requires a lot of time and effort.

## 4-Solution 
This is why automating this process, transforming a Docker compose file
to Terraform config files, using a model transformation language can help speed
up the development process and reduce the amount of time and effort needed
to complete the task. This can help streamline the deployment process, allowing
developers to deploy their applications to Kubernetes clusters faster and more
reliably. In this project we used ETL(Extract, transform, Load) for the transformation process :

![ETL-Pipeline](https://github.com/IrisTheSnail/compose2terraform/assets/91791764/43e0f970-893e-4d20-baad-4fc04ba4446e)


### Source Metamodel: model for a Docker compose file
![image](https://github.com/IrisTheSnail/compose2terraform/assets/91791764/b10d9306-22e2-4d6b-b979-e88e844e6b5c)

### Target Metamodel: model for a Terraform config file
![image](https://github.com/IrisTheSnail/compose2terraform/assets/91791764/4f11c13c-f180-46f1-89d9-4251333ed403)

### Model Transformation : definition

In the context of model-driven engineering, model transformation aims
to to specify how to produce target models from a set of source models. This
allows developers to define how the elements of the source model should be used
to initialize the elements of the target model.

# Conclusion


In conclusion, compose2terraform automates the conversion of Docker Compose files to Terraform configurations using Model Driven Engineering and an ETL pipeline. This efficient transformation accelerates development and enhances deployment reliability.

