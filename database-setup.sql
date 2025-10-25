-- CODEXA Database Setup Script
-- Run this in MySQL Workbench or MySQL command line

-- Create database if it doesn't exist
CREATE DATABASE IF NOT EXISTS jewellery;

-- Use the database
USE jewellery;

-- Create user if it doesn't exist (MySQL 8.0+)
CREATE USER IF NOT EXISTS 'appuser'@'localhost' IDENTIFIED BY 'app_password';

-- Grant privileges
GRANT ALL PRIVILEGES ON jewellery.* TO 'appuser'@'localhost';
FLUSH PRIVILEGES;

-- Check if tables exist and create sample data
-- (The tables should be created by Flyway migrations when the app starts)

-- Sample data (run after the app has started and created tables)
INSERT IGNORE INTO category (category_name) VALUES 
('Rings'),
('Necklaces'),
('Bracelets'),
('Earrings');

INSERT IGNORE INTO metal (metal_type, metal_purity) VALUES 
('GOLD', '22K'),
('SILVER', '925'),
('PLATINUM', '950');

INSERT IGNORE INTO gem (gem_name, karat_rate) VALUES 
('Diamond', 50000.00),
('Ruby', 25000.00),
('Sapphire', 30000.00),
('Emerald', 20000.00);

-- Check what we have
SELECT 'Categories:' as info;
SELECT * FROM category;

SELECT 'Metals:' as info;
SELECT * FROM metal;

SELECT 'Gems:' as info;
SELECT * FROM gem;

SELECT 'Products:' as info;
SELECT COUNT(*) as product_count FROM product;

