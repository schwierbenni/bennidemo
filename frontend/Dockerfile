# Use the official Nginx image as the base image
FROM nginx:latest

# Copy the local HTML file to the default Nginx web root
COPY templates/* /usr/share/nginx/html/

# Expose port 80 for Nginx
EXPOSE 80

# Command to start Nginx
CMD ["nginx", "-g", "daemon off;"]