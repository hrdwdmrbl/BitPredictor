class Prediction < ActiveRecord::Base
  attr_accessible :end_date, :name, :prediction, :source, :verified_by
end
