
MODULE=$1
echo "$MODULE"

MODULE_PATH="cell-${MODULE}"
MMODULE="$(tr '[:lower:]' '[:upper:]' <<< ${MODULE:0:1})${MODULE:1}"

rm -rf cell-aaaa/target/

cp -r cell-aaaa "$MODULE_PATH"

sed -i "" "s/aaaa/$MODULE/g" `grep "aaaa" -rl ${MODULE_PATH} | grep -v "$MODULE/target"`
sed -i "" "s/Cell${MODULE}DatasourceConfig/Cell${MMODULE}DatasourceConfig/g" `grep "Cell${MODULE}DatasourceConfig" -rl ${MODULE_PATH}`
sed -i "" "s/Cell${MODULE}AutoConfiguration/Cell${MMODULE}AutoConfiguration/g" `grep "Cell${MODULE}AutoConfiguration" -rl ${MODULE_PATH}`

mv ${MODULE_PATH}/src/main/java/tech/qijin/cell/aaaa  "${MODULE_PATH}/src/main/java/tech/qijin/cell/${MODULE}"
echo ${MMODULE}
mv ${MODULE_PATH}/src/main/java/tech/qijin/cell/${MODULE}/config/CellaaaaDatasourceConfig.java "${MODULE_PATH}/src/main/java/tech/qijin/cell/${MODULE}/config/Cell${MMODULE}DatasourceConfig.java"
mv ${MODULE_PATH}/src/main/java/tech/qijin/cell/${MODULE}/config/CellaaaaAutoConfiguration.java "${MODULE_PATH}/src/main/java/tech/qijin/cell/${MODULE}/config/Cell${MMODULE}AutoConfiguration.java"

