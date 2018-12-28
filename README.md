# pokezen

## Start

    docker-compose -f docker-compose.yml up

Go to: http://localhost:51265


## Development

    docker-compose up

After stopping it, you can also start and stop the containers individually:
* `pokezen_scalapi`: backend
* `pokezen_reactfront`: frontend

### Backend
The backend starts in the sbt shell. Attach to it and run the tests, run the development server, etc.

    docker attach pokezen_scalapi
    run # or test, ...

http://localhost:51264


### Frontend
For the frontend too: attach to it and run the tests, the development server, etc.

    docker attach pokezen_reactfront
    npm start # or npm run test, build, ...

http://localhost:51265
