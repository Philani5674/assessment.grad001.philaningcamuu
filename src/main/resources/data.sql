
-- Sample data for waste_categories
INSERT INTO waste_category (name, description) VALUES
('Plastic', 'All types of plastic materials including bottles, containers, and packaging'),
('Paper', 'Paper products including cardboard, newspapers, and magazines'),
('Glass', 'Glass bottles, jars, and broken glass items'),
('Metal', 'Metal items including cans, foil, and scrap metal'),
('Organic', 'Food waste, garden waste, and other biodegradable materials'),
('Electronic', 'Electronic devices, batteries, and related components');

-- Sample data for waste_items
INSERT INTO waste_item (name, description, category_id) VALUES
('Plastic Bottles', 'PET and HDPE bottles', 1),
('Plastic Bags', 'Shopping bags and packaging', 1),
('Newspapers', 'Daily newspapers and magazines', 2),
('Cardboard Boxes', 'Shipping boxes and packaging', 2),
('Glass Bottles', 'Beverage bottles and containers', 3),
('Aluminum Cans', 'Beverage cans and food containers', 4),
('Food Scraps', 'Kitchen waste and leftovers', 5),
('Smartphones', 'Old and broken mobile phones', 6);

-- Sample data for recycling_tips
INSERT INTO recycling_tip (title, content, waste_item_id, created_at) VALUES
('Proper Bottle Cleaning', 'Rinse bottles thoroughly and remove caps before recycling', 1, CURRENT_TIMESTAMP),
('Bag Reuse Ideas', 'Creative ways to reuse plastic bags or recycle them properly', 2, CURRENT_TIMESTAMP),
('Paper Sorting Guide', 'How to sort different types of paper for recycling', 3, CURRENT_TIMESTAMP),
('Cardboard Preparation', 'Break down boxes and remove tape before recycling', 4, CURRENT_TIMESTAMP),
('Glass Recycling Safety', 'Safety tips for handling glass during recycling', 5, CURRENT_TIMESTAMP),
('Aluminum Can Recycling', 'Benefits of recycling aluminum cans and how to do it', 6, CURRENT_TIMESTAMP),
('Composting Basics', 'Introduction to composting and its benefits for the environment', 7, CURRENT_TIMESTAMP),
('E-Waste Disposal Options', 'How to dispose of old electronics responsibly', 8, CURRENT_TIMESTAMP),
('Plastic Recycling Numbers', 'Understanding the recycling codes on plastic items', 1, CURRENT_TIMESTAMP),
('Recycling Paper Bags', 'Tips for recycling paper bags and reducing waste', 2, CURRENT_TIMESTAMP),
('Glass Recycling Process', 'Step-by-step guide to recycling glass bottles and jars', 3, CURRENT_TIMESTAMP),
('Metal Recycling Benefits', 'Environmental benefits of recycling metal items', 4, CURRENT_TIMESTAMP),
('Organic Waste Collection', 'How to collect and compost organic waste at home', 5, CURRENT_TIMESTAMP),
('E-Waste Recycling Centers', 'Locations of e-waste recycling centers in the city', 6, CURRENT_TIMESTAMP);

-- Sample data for disposal_guidelines
INSERT INTO disposal_guideline (category_id, title, instructions, environmental_impact, created_at, updated_at) VALUES
(1, 'Plastic Recycling Guide', 'Check the recycling number, clean the item, and place in recycling bin', 'Reduces landfill waste and oil consumption', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Paper Disposal Guidelines', 'Keep paper dry and separate from other materials', 'Saves trees and reduces landfill usage', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Glass Recycling Instructions', 'Sort by color and handle with care', 'Reduces energy use in glass production', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Metal Recycling Process', 'Clean items and separate by type of metal', 'Reduces mining impact and energy usage', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Organic Waste Composting', 'Mix green and brown materials properly', 'Creates natural fertilizer and reduces methane', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
