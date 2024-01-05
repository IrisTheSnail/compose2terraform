# compose2terraform
**Transforms your docker-compose file to a Terraform config file!**

# Introduction
Model Driven Engineering (MDE) is a software development approach
that focuses on creating and exploiting domain models as the core artifacts of soft-
ware development. It is based on the idea of separating the development process
into two distinct parts : the design of the model and the generation of the software
from the model. The goal of MDE is to create a model of the system that can be
used to drive the development process, allowing for faster development cycles and
improved quality.

# Context of the project

## 1-Compose file 
Compose file is a YAML file that defines services, networks and volumes
for a Docker application. It is used to configure an application’s services, making
it easier to manage the application’s containers. Compose file contains all the in-
formation needed to run an application, including the application’s dependencies,
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
reliably. In this project we used ETL(Extract, transform, Load) for this transformation :


## Meta-metamodels
### Docker-compose
### Terraform

## etl

