## Cucumber Integration Test

#### Running Integration Tests
To execute tests, connect to the services deployed on Ireland Dev Environment,via SSH tunnels or start the service locally(refer _Note_ below). Example -  

For Payment Service, execute the command via terminal

`
ssh -N -L 9008:internal-player-pay-development-1967698457.eu-west-1.elb.amazonaws.com:80 ubuntu@ec2-54-229-210-25.eu-west-1.compute.amazonaws.com 
`

After establishing a connection, run the feature `/src/test/resources/com/casumo/rest/integration/payment-feature`.

_Note: For running `payment.feature` on local instance of service, update port number in the URLs specified under `Background: Given` section of `payment.feature` test case, for port number refer to `application.yml` of `casumobet-player-payment-service`._

##### SSH Commands
Run command to connect to respective services - 

- Football Bet Service - `ssh -N -L 9004:internal-wallet-development-108394219.eu-west-1.elb.amazonaws.com:80 ubuntu@ec2-54-229-210-25.eu-west-1.compute.amazonaws.com`
- Player Bet Service - `ssh -N -L 9003:internal-player-bet-development-1600467235.eu-west-1.elb.amazonaws.com:80 ubuntu@ec2-54-229-210-25.eu-west-1.compute.amazonaws.com`
- Wallet Service - `ssh -N -L 9004:internal-wallet-development-108394219.eu-west-1.elb.amazonaws.com:80 ubuntu@ec2-54-229-210-25.eu-west-1.compute.amazonaws.com`
- Bookie Service - `ssh -N -L 9005:bookie-development-675518807.eu-west-1.elb.amazonaws.com:80 ubuntu@ec2-54-229-210-25.eu-west-1.compute.amazonaws.com`
- Cards Service - `ssh -N -L 9006:internal-cards-development-2058661597.eu-west-1.elb.amazonaws.com:80 ubuntu@ec2-54-229-210-25.eu-west-1.compute.amazonaws.com`
- Player Cards Service - `ssh -N -L 9007:internal-player-cards-development-1483404106.eu-west-1.elb.amazonaws.com:80 ubuntu@ec2-54-229-210-25.eu-west-1.compute.amazonaws.com`
- Payment Service - `ssh -N -L 9008:internal-player-pay-development-1967698457.eu-west-1.elb.amazonaws.com:80 ubuntu@ec2-54-229-210-25.eu-west-1.compute.amazonaws.com`
- Player Auth Service - `ssh -N -L 9009:internal-player-auth-development-957937057.eu-west-1.elb.amazonaws.com:80 ubuntu@ec2-54-229-210-25.eu-west-1.compute.amazonaws.com`
- Player Registration Service - `ssh -N -L 9010:internal-player-reg-development-1629586677.eu-west-1.elb.amazonaws.com:80 ubuntu@ec2-54-229-210-25.eu-west-1.compute.amazonaws.com`
- Authentication Service (Deprecated) - `ssh -N -L 19009:internal-auth-development-1636032706.eu-west-1.elb.amazonaws.com:80 ubuntu@ec2-54-229-210-25.eu-west-1.compute.amazonaws.com`

For more information check [this document.](https://github.com/Casumo/external-sports/blob/master/docs/infrastructure/Environments-Architecture.md)