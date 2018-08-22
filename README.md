# Hi, Anole
Anole is a online real-time configuration managerment system. By using it, you can easily modify your configs referenced by a lot of distributed applications.

## Architecture
![](https://github.com/tbwork/anole/blob/master/architecture.png?raw=true)

## Roles in Anole
![](https://github.com/tbwork/anole/blob/master/roles.png?raw=true)

As shown in the upper picture, there are four roles in Anole. They are:
* **Subsciber**: the client side of Anole to retrieve configs. It watches the config update events and update them automatically to make sure its owner application can retrieve the latest value of those configs.
* **Publisher**: the client side of Anole to publish config changes. It posts config changes to the boss so that the boss persist the change to harddisk and then broadcast this change.
* **Worker**: the server side of Anole to serve subscribers. It is assigned to the subscriber by the boss server.
* **Boss**: the server side of Anole to serve publishers and workers. It also serves the subscribers at the authentication phase.

Let's elaborate the two main procedures in Anole: how does one subscriber connect to the worker and how to publish a config change. Following is the first:
1. The subscriber connect to the boss to ask for service. The boss verify its identification first and select one best worker for the subscriber.
2. The boss tell the subscriber the target worker it should connect to.
3. The subscriber connect to the target worker.
4. The subscriber subscribes its interested configs in worker server. Then, the worker knows the subscriber's interested configs.

Now let's see how subscriber retrieve the latest value of config when it is modified:
1. User modify certain config via Anole-UI, this will call the inner publisher to push the change to the Boss. 
2. The boss server receives the change and persists it on harddisk, and then it broadcast the change to all workers.
3. Each worker server checks which subscribers are interested in this config, and then sends this change to all related subscribers.






