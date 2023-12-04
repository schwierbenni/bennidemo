docker build -t your-spring-app-image:latest .

docker-compose -f docker-compose.yml stop && docker-compose -f docker-compose.yml rm -f  && docker-compose -f docker-compose.yml up