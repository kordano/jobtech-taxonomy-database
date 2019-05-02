(ns jobtech-taxonomy-database.types
  (:gen-class)
  (:require
   [camel-snake-kebab.core :as csk]
   )
  )



(def continent "continent")
(def country "country")
(def driving-license "driving_license")
(def employment-duration "employment_duration")
(def employment-type "employment_type")
(def isco "isco")
(def keyword "keyword")
(def language "language")
(def language-level "language_level")
(def municipality "municipality")
(def occupation-collection "occupation_collection")
(def occupation-field "occupation_field")
(def occupation-group "occupation_group")
(def occupation-name "occupation_name")
(def region "region")
(def skill "skill")
(def skill-headline "skill_headline")
(def skill-main-headline "skill_main_headline")
(def ssyk-level-1 "ssyk_level_1")
(def ssyk-level-2 "ssyk_level_2")
(def ssyk-level-3 "ssyk_level_3")
(def sun-education-field-1 "sun_education_field_1")
(def sun-education-field-2 "sun_education_field_2")
(def sun-education-field-3 "sun_education_field_3")
(def sun-education-level-1 "sun_education_level_1")
(def sun-education-level-2 "sun_education_level_2")
(def sun-education-level-3 "sun_education_level_3")
(def wage-type "wage_type")
(def worktime-extent "worktime_extent")






(comment


  (def dump [
             [
              "continent"
              ],
             [
              "country"
              ],
             [
              "driving-license"
              ],
             [
              "employment-duration"
              ],
             [
              "employment-type"
              ],
             [
              "isco"
              ],
             [
              "keyword"
              ],
             [
              "language"
              ],
             [
              "language-level"
              ],
             [
              "municipality"
              ],
             [
              "occupation-collection"
              ],
             [
              "occupation-field"
              ],
             [
              "occupation-group"
              ],
             [
              "occupation-name"
              ],
             [
              "region"
              ],
             [
              "skill"
              ],
             [
              "skill-headline"
              ],
             [
              "skill-main-headline"
              ],
             [
              "ssyk-level-1"
              ],
             [
              "ssyk-level-2"
              ],
             [
              "ssyk-level-3"
              ],
             [
              "sun-education-field-1"
              ],
             [
              "sun-education-field-2"
              ],
             [
              "sun-education-field-3"
              ],
             [
              "sun-education-level-1"
              ],
             [
              "sun-education-level-2"
              ],
             [
              "sun-education-level-3"
              ],
             [
              "wage-type"
              ],
             [
              "worktime-extent"
              ]
             ])


  (defn generate-def [type]
    (println (str "(def "  (first type)  " \""  (csk/->snake_case_string (first type))   "\""      ")" ))
    )

  )
