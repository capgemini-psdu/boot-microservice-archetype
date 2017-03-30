# Stop the microservice
echo 'Stopping ${microserviceGroupName}-${artifactId}'
sudo stop ${microserviceGroupName}-${artifactId}

# Start the microservice
echo 'Starting ${microserviceGroupName}-${artifactId}'
sudo start ${microserviceGroupName}-${artifactId}
