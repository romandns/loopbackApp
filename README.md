# list of projects

1. 'loopback' project is related to the node.js app
    - contains simply Gherkin scenario for e2e tests
    - contains step definitions for each model JS app (loopback)
    - contains env.properties for extracting base URL for passing it , via system properties (VM)

2.'student' project is related to the node.js app
    - contains data generator. Can generate test file in different formats (JSON, XML, CSV) which are using for
      creating new records in DB

    prerequisites:
        'student' app location GIT link - https://github.com/ooge0/loopbackMongoUi.git
        mongoDB server - https://www.mongodb.com/download-center/community
        Robo 3T - mongoDB management tool (https://robomongo.org/)
        loopback framework - instruction: https://medium.freecodecamp.org/build-restful-api-with-authentication-under-5-minutes-using-loopback-by-expressjs-no-programming-31231b8472ca

                                 `. ___
                                __,' __`.                _..----....____
                    __...--.'``;.   ,.   ;``--..__     .'    ,-._    _.-'
              _..-''-------'   `'   `'   `'     O ``-''._   (,;') _,'
            ,'________________                          \`-._`-','
             `._              ```````````------...___   '-.._'-:
                ```--.._      ,.                     ````--...__\-.
                        `.--. `-`                       ____    |  |`
                          `. `.                       ,'`````.  ;  ;`
                            `._`.        __________   `.      \'__/`
                               `-:._____/______/___/____`.     \  `
                                           |       `._    `.    \
                                           `._________`-.   `.   `.___
                                                         SSt  `------'`


