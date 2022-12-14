#!/bin/bash
BUILD_JAR=$(ls /home/ubuntu/action/back/build/libs/parking-go-0.0.1-SNAPSHOT.jar)
JAR_NAME=$(basename $BUILD_JAR)

echo "> 현재 시간: $(date)" >> /home/ubuntu/action/deploy.log

echo "> build 파일명: $JAR_NAME" >> /home/ubuntu/action/deploy.log

echo "> build 파일 복사" >> /home/ubuntu/action/deploy.log
DEPLOY_PATH=/home/ubuntu/action/
cp $BUILD_JAR $DEPLOY_PATH

echo "> 현재 실행중인 애플리케이션 pid 확인" >> /home/ubuntu/action/deploy.log
CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> /home/ubuntu/action/deploy.log
else
  echo "> kill -15 $CURRENT_PID" >> /home/ubuntu/action/deploy.log
  sudo kill -15 $CURRENT_PID
  sleep 15
fi

DEPLOY_JAR=$DEPLOY_PATH$JAR_NAME
echo "> DEPLOY_JAR 배포"    >> /home/ubuntu/action/deploy.log
ENCRYPT_KEY=$(cat /home/encrypto_key.txt)
sudo nohup java -Djasypt.encryptor.password=$ENCRYPT_KEY -jar $DEPLOY_JAR >> /home/ubuntu/deployBe.log 2>/home/ubuntu/action/deploy_err.log &

BUILD_FRONT=/home/ubuntu/action/front/build/
echo "> 현재 실행중인 리액트 pid 확인" >> /home/ubuntu/action/deploy.log
REACT_PID=$(pgrep -f node)

if [ -z $REACT_PID ]
then
  echo "> 현재 구동중인 노드가 없으므로 종료하지 않습니다." >> /home/ubuntu/action/deploy.log
else
  echo "> kill -15 $REACT_PID" >> /home/ubuntu/action/deploy.log
  sudo kill -15 $REACT_PID
  sleep 5
fi

echo "> 프론트 서버 배포" >> /home/ubuntu/action/deploy.log
sudo ln -s /home/ubuntu/.nvm/versions/node/v16.17.1/bin/node /usr/bin/node
sudo ln -s /home/ubuntu/.nvm/versions/node/v16.17.1/bin/npm /usr/bin/npm
sudo nohup /home/ubuntu/.nvm/versions/node/v16.17.1/bin/serve -s $BUILD_FRONT >> /home/ubuntu/deployFe.log 2> /home/ubuntu/deploy_Fe_err.log &

echo "> ============== 끝 ==============" >> /home/ubuntu/action/deploy.log
#!/bin/bash
BUILD_JAR=$(ls /home/ubuntu/action/back/build/libs/parking-go-0.0.1-SNAPSHOT.jar)
JAR_NAME=$(basename $BUILD_JAR)

echo "> 현재 시간: $(date)" >> /home/ubuntu/action/deploy.log

echo "> build 파일명: $JAR_NAME" >> /home/ubuntu/action/deploy.log

echo "> build 파일 복사" >> /home/ubuntu/action/deploy.log
DEPLOY_PATH=/home/ubuntu/action/
cp $BUILD_JAR $DEPLOY_PATH

echo "> 현재 실행중인 애플리케이션 pid 확인" >> /home/ubuntu/action/deploy.log
CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> /home/ubuntu/action/deploy.log
else
  echo "> kill -15 $CURRENT_PID" >> /home/ubuntu/action/deploy.log
  sudo kill -15 $CURRENT_PID
  sleep 15
fi

DEPLOY_JAR=$DEPLOY_PATH$JAR_NAME
echo "> DEPLOY_JAR 배포"    >> /home/ubuntu/action/deploy.log
ENCRYPT_KEY=$(cat /home/encrypto_key.txt)
sudo nohup java -Djasypt.encryptor.password=$ENCRYPT_KEY -jar $DEPLOY_JAR >> /home/ubuntu/deployBe.log 2>/home/ubuntu/action/deploy_err.log &

BUILD_FRONT=/home/ubuntu/action/front/build/
echo "> 현재 실행중인 리액트 pid 확인" >> /home/ubuntu/action/deploy.log
REACT_PID=$(pgrep -f node)

if [ -z $REACT_PID ]
then
  echo "> 현재 구동중인 노드가 없으므로 종료하지 않습니다." >> /home/ubuntu/action/deploy.log
else
  echo "> kill -15 $REACT_PID" >> /home/ubuntu/action/deploy.log
  sudo kill -15 $REACT_PID
  sleep 15
fi

echo "> 프론트 서버 배포" >> /home/ubuntu/action/deploy.log
sudo ln -s /home/ubuntu/.nvm/versions/node/v16.17.1/bin/node /usr/bin/node
sudo ln -s /home/ubuntu/.nvm/versions/node/v16.17.1/bin/npm /usr/bin/npm
sudo nohup /home/ubuntu/.nvm/versions/node/v16.17.1/bin/serve -s $BUILD_FRONT >> /home/ubuntu/deployFe.log 2> /home/ubuntu/deploy_Fe_err.log &

echo "> ============== 끝 ==============" >> /home/ubuntu/action/deploy.log
