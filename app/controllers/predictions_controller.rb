class PredictionsController < InheritedResources::Base
  def create
    params[:user_id] = current_user.id
    create!
  end
end
