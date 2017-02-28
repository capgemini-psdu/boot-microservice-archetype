#!/bin/bash
service=$1
versionToInstall=$2
suffix=$3

echo 'About to check if rpm/service is installed'
rpm -q $service

if [ $? == 0 ]
then
  echo 'rpm already installed'
  installed_version=`rpm -q --queryformat '%{version}\n' $service`
  echo "Installed version of rpm is: ${installed_version}"
  echo "Version of the rpm being installed is: ${versionToInstall}"
  
  sudo yum remove -y $service-$installed_version$suffix

else
  echo 'rpm not already installed'
fi
sudo yum install -y $service-$versionToInstall$suffix.rpm
echo 'rpm now installed'
