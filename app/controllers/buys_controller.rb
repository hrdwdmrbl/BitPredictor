class BuysController < InheritedResources::Base
    belongs_to :prediction, :optional => true
    def create
        params[:buy][:user_id] = current_user.id
        create!
    end
end
