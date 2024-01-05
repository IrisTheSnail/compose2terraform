provider "docker" {
    version = "~> 2.13"
}
resource "docker_container" "web" {
    image      = "nginx"
    networks    = ["frontend"]
    volumes     = ["./app:/app"]
    environment = ["NGINX_PORT=80"]
}
resource "docker_container" "api" {
    image      = "myapi:latest"
    networks    = ["frontend", "backend"]
    volumes     = ["./api:/api"]
    environment = ["API_PORT=3000"]
}
resource "docker_network" "frontend" {
    name   = "frontend"
    driver = "bridge"
}
resource "docker_network" "backend" {
    name   = "backend"
    driver = "bridge"
}
