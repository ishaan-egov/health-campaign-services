CREATE INDEX idx_eg_cm_campaign_details_status ON eg_cm_campaign_details(status);
CREATE INDEX idx_eg_cm_campaign_details_campaignname ON eg_cm_campaign_details(campaignname);
CREATE INDEX idx_eg_cm_campaign_process_campaignid ON eg_cm_campaign_process(campaignid);
CREATE INDEX idx_eg_cm_generated_resource_details_campaignid ON eg_cm_generated_resource_details(campaignid);
CREATE INDEX idx_eg_cm_resource_details_campaignid ON eg_cm_resource_details(campaignid);
