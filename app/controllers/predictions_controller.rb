class PredictionsController < InheritedResources::Base
  def create
    params[:prediction][:user_id] = current_user.id
    create!
  end
end
