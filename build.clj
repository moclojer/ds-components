(ns build
  (:refer-clojure :exclude [test])
  (:require
   [clojure.tools.build.api :as b]))

(def lib 'com.github.mockingbird/components)

(def version "0.0.1-SNAPSHOT")

(def class-dir "target/classes")

(def jar-file (format "target/%s-%s.jar" (name lib) version))

(def basis (delay (b/create-basis {:project "deps.edn"})))

(defn clean [_]
  (b/delete {:path "target"}))

(defn jar [_]
  (b/write-pom {:class-dir class-dir
                :lib lib
                :version version
                :basis @basis
                :src-dirs ["src"]})
  (b/copy-dir {:src-dirs ["src" "resources"]
               :target-dir class-dir})
  (b/jar {:class-dir class-dir
          :jar-file jar-file}))
