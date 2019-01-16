(ns jobtech-taxonomy-database.converters.employment-type-converter
  (:gen-class)
  (:require [datomic.client.api :as d]
            [jobtech-taxonomy-database.schema :refer :all :as schema]
            [jobtech-taxonomy-database.legacy-migration :refer :all]
            [jobtech-taxonomy-database.config :refer :all]
            [jobtech-taxonomy-database.datomic-connection :refer :all :as conn]))

(defn ^:private fake-id
  "Temporary function until new id function with nano-ids is imported into this project."
  [id]
  (format "%010d" id))

(defn converter
  "Immutable language converter."
  [data]
  [{:concept/id (str  (:anstallningtypjobbid  data))
    :concept/description       (:beteckning data)
    :concept/preferred-term     (str  (:anstallningtypjobbid  data))
    :concept/alternative-terms #{(str  (:anstallningtypjobbid  data))}}
   {:db/id  (str  (:anstallningtypjobbid  data))
    :term/base-form (:beteckning data)}])

(defn convert
  ""
  []
  (mapcat converter  (fetch-data get-employment-type)))