#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/ry_20250523.sql ./mysql/db
cp ../sql/ry_config_20250224.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../quizmate-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy quizmate-gateway "
cp ../quizmate-gateway/target/quizmate-gateway.jar ./quizmate/gateway/jar

echo "begin copy quizmate-auth "
cp ../quizmate-auth/target/quizmate-auth.jar ./quizmate/auth/jar

echo "begin copy quizmate-visual "
cp ../quizmate-visual/quizmate-monitor/target/quizmate-visual-monitor.jar  ./quizmate/visual/monitor/jar

echo "begin copy quizmate-modules-system "
cp ../quizmate-modules/quizmate-system/target/quizmate-modules-system.jar ./quizmate/modules/system/jar

echo "begin copy quizmate-modules-file "
cp ../quizmate-modules/quizmate-file/target/quizmate-modules-file.jar ./quizmate/modules/file/jar

echo "begin copy quizmate-modules-job "
cp ../quizmate-modules/quizmate-job/target/quizmate-modules-job.jar ./quizmate/modules/job/jar

echo "begin copy quizmate-modules-gen "
cp ../quizmate-modules/quizmate-gen/target/quizmate-modules-gen.jar ./quizmate/modules/gen/jar

