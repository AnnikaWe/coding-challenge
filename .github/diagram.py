from diagrams import Diagram
from diagrams.aws.compute import EC2
from diagrams.aws.network import VPC

with Diagram("Architecture", show=False):
    vpc = VPC("VPC")
    ec2 = EC2("EC2 Instance")
    vpc >> ec2
