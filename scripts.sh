function create-class () {
  echo "package com.eightnote.nirvana.$1;" >> "$2"
  echo "" >> "$2"
  echo "public class ${entity^}$3 {" >> "$2"
  echo "" >> "$2"
  echo "}" >> "$2"
}

function create-files() {
  entity=$1
  controller="src/main/java/com/eightnote/nirvana/controllers/${entity^}Controller.java"
  dao="src/main/java/com/eightnote/nirvana/DAOs/${entity^}DAO.java"
  model="src/main/java/com/eightnote/nirvana/models/${entity^}.java"
  service="src/main/java/com/eightnote/nirvana/services/${entity^}Service.java"
  row_mapper="src/main/java/com/eightnote/nirvana/row_mappers/${entity^}RowMapper.java"

  true ">$controller"
  create-class "controllers" "$controller" "Controller"

  true ">$dao"
  create-class "DAOs" "$dao" "DAO"

  true ">$row_mapper"
  create-class "row_mappers" "$row_mapper" "RowMapper"

  true ">$model"
  create-class "models" "$model" ""

  true ">$service"
  create-class "services" "$service" "Service"

  echo "Created $controller"
  echo "Created $dao"
  echo "Created $model"
  echo "Created $row_mapper"
  echo "Created $service"
}

function delete-files() {
  entity=$1

  controller="src/main/java/com/eightnote/nirvana/controllers/${entity^}Controller.java"
  dao="src/main/java/com/eightnote/nirvana/DAOs/${entity^}DAO.java"
  model="src/main/java/com/eightnote/nirvana/models/${entity^}.java"
  service="src/main/java/com/eightnote/nirvana/services/${entity^}Service.java"
  row_mapper="src/main/java/com/eightnote/nirvana/row_mappers/${entity^}RowMapper.java"

  rm "$controller"
  rm "$dao"
  rm "$service"
  rm "$model"
  rm "$row_mapper"
}

if [ $# != 2 ]; then
  echo "Invalid args"
  exit 1
fi

if [ "$1" == 'create' ]; then
  create-files "$2"
fi

if [ "$1" == 'del' ]; then
  delete-files "$2"
fi
