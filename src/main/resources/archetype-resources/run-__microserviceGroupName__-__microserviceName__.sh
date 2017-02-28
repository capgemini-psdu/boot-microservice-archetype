# Stop the microservice
echo 'Stopping ${microserviceGroupName}-${microserviceName}'
sudo stop ${microserviceGroupName}-${microserviceName}

# Start the microservice
echo 'Starting ${microserviceGroupName}-${microserviceName}'
sudo start ${microserviceGroupName}-${microserviceName}
