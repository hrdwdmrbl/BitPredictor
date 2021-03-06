# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended to check this file into your version control system.

ActiveRecord::Schema.define(:version => 20130323210728) do

  create_table "buys", :force => true do |t|
    t.decimal  "number_of_shares"
    t.integer  "user_id"
    t.integer  "prediction_id"
    t.datetime "created_at",       :null => false
    t.datetime "updated_at",       :null => false
    t.float    "price"
    t.integer  "transaction_id"
  end

  create_table "holds", :force => true do |t|
    t.integer  "user_id"
    t.float    "value"
    t.string   "holdable_type"
    t.integer  "holdable_id"
    t.datetime "created_at",    :null => false
    t.datetime "updated_at",    :null => false
  end

  create_table "predictions", :force => true do |t|
    t.string   "name"
    t.datetime "end_date"
    t.text     "prediction"
    t.string   "source"
    t.integer  "verified_by"
    t.datetime "created_at",  :null => false
    t.datetime "updated_at",  :null => false
    t.integer  "user_id"
    t.string   "outcome"
  end

  create_table "sells", :force => true do |t|
    t.decimal  "number_of_shares"
    t.integer  "user_id"
    t.datetime "created_at",       :null => false
    t.datetime "updated_at",       :null => false
    t.integer  "prediction_id"
    t.float    "price"
    t.integer  "transaction_id"
  end

  create_table "transactions", :force => true do |t|
    t.integer  "buy_id"
    t.integer  "sell_id"
    t.integer  "price"
    t.integer  "number_of_shares"
    t.integer  "prediction_id"
    t.datetime "created_at",       :null => false
    t.datetime "updated_at",       :null => false
  end

  create_table "users", :force => true do |t|
    t.string   "email",                  :default => "", :null => false
    t.string   "encrypted_password",     :default => "", :null => false
    t.string   "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.integer  "sign_in_count",          :default => 0
    t.datetime "current_sign_in_at"
    t.datetime "last_sign_in_at"
    t.string   "current_sign_in_ip"
    t.string   "last_sign_in_ip"
    t.datetime "created_at",                             :null => false
    t.datetime "updated_at",                             :null => false
    t.string   "provider"
    t.string   "uid"
    t.string   "name"
    t.string   "public_key"
    t.string   "private_key"
  end

  add_index "users", ["email"], :name => "index_users_on_email", :unique => true
  add_index "users", ["reset_password_token"], :name => "index_users_on_reset_password_token", :unique => true

end
