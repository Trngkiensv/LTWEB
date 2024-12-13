-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 09, 2023 lúc 07:31 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `webshop`
--
CREATE DATABASE IF NOT EXISTS `webshop` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `webshop`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart`
--

CREATE TABLE `cart` (
  `cartID` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `feature` varchar(50) DEFAULT NULL,
  `client_clientID` int(11) NOT NULL,
  `product_productID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `cart`
--

INSERT INTO `cart` (`cartID`, `quantity`, `feature`, `client_clientID`, `product_productID`) VALUES
(24, 1, '', 2, 6),
(28, 1, '', 1, 6),
(29, 1, '', 1, 8),
(30, 1, '', 1, 9);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `categoryID` int(11) NOT NULL,
  `nameCategory` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`categoryID`, `nameCategory`) VALUES
(1, 'Thời Trang'),
(2, 'Đồ Điện Tử'),
(3, 'Đồ Gia Dụng'),
(4, 'Thực Phẩm'),
(6, 'Dụng Cụ'),
(7, 'Học Tập'),
(8, 'Trang Trí'),
(9, 'Mỹ Phẩm'),
(10, 'Giày Dép'),
(11, 'Thực Phẩm chức năng'),
(12, 'Giải Trí'),
(14, 'Thú Cưng'),
(15, 'Giặt Giũ'),
(16, 'Ốp Lưng'),
(17, 'Trẻ Em');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `client`
--

CREATE TABLE `client` (
  `clientID` int(11) NOT NULL,
  `user` varchar(50) DEFAULT NULL,
  `pass` varchar(50) DEFAULT NULL,
  `money` varchar(20) DEFAULT NULL,
  `fullname` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `image` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `client`
--

INSERT INTO `client` (`clientID`, `user`, `pass`, `money`, `fullname`, `birthday`, `address`, `phone`, `image`) VALUES
(1, 'hung14', '123456', '90645001', 'Hùng Dũng', '2000-06-19', '122 Hoàng Thụ', '0799994566', '/DataImage/user/avatar_userID1'),
(2, 'hung123', '00000000', '14500000', 'Pham Hung', '2022-05-06', '135 Thái thị bôi', '0777465394', '/DataImage/user/avatar_userID2'),
(3, 'nguyen', '456789', '14500000', 'Nguyễn Nguyên', '2000-11-17', '122 Hoàng Chiến', '0906458725', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `comment`
--

CREATE TABLE `comment` (
  `commentID` int(11) NOT NULL,
  `comment` varchar(100) DEFAULT NULL,
  `client_clientID` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `product_productID` int(11) NOT NULL,
  `rating` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `comment`
--

INSERT INTO `comment` (`commentID`, `comment`, `client_clientID`, `date`, `product_productID`, `rating`) VALUES
(2, 'sản phẩm tốt\r\n', 1, '2022-05-12 10:40:17', 5, NULL),
(3, 'sản phẩm tốt', 1, '2022-05-12 10:44:58', 5, NULL),
(4, 'Sản phẩm tốt', 1, '2022-05-12 13:00:57', 4, NULL),
(5, 'Sản phẩm tốt xungwss đáng 100Đ', 1, '2022-05-12 13:19:54', 2, NULL),
(6, 'Sản phẩm tốt xungwss đáng 100Đ', 1, '2022-05-12 13:22:43', 2, NULL),
(7, 'Sản phẩm tốt xungwss đáng 100Đ', 1, '2022-05-12 13:22:53', 2, NULL),
(8, 'Sản Phẩm quá tệ ..................................??????', 2, '2022-05-12 14:01:44', 2, NULL),
(9, 'Sản Phẩm rất tốt', 1, '2022-05-12 18:51:01', 6, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `payment`
--

CREATE TABLE `payment` (
  `paymentID` int(11) NOT NULL,
  `cardholderName` varchar(50) DEFAULT NULL,
  `nameAccount` varchar(50) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `clientID` int(11) NOT NULL,
  `money` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `productID` int(11) NOT NULL,
  `product` varchar(100) DEFAULT NULL,
  `priceO` varchar(20) DEFAULT NULL,
  `priceS` varchar(20) DEFAULT NULL,
  `img` varchar(200) DEFAULT NULL,
  `numsold` varchar(20) DEFAULT NULL,
  `shop_shopID` int(11) NOT NULL,
  `category_categoryID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`productID`, `product`, `priceO`, `priceS`, `img`, `numsold`, `shop_shopID`, `category_categoryID`) VALUES
(1, 'Apple MacBook Air (2020)     ', '27950000', '27950000', 'https://cf.shopee.vn/file/575e04c0e1d08b5f1b9f624a8d6b1419', '1.4 k', 1, 2),
(2, 'Apple MacBook Air (2020) ', '27950000', '25950000', 'https://cf.shopee.vn/file/575e04c0e1d08b5f1b9f624a8d6b1419', '24,4 k', 2, 2),
(4, 'Apple Smart Keyboard Folio For iPad Pro ', '5999000', '4999999', 'https://cf.shopee.vn/file/fabe5d101de9ff01342f89a2f056aa12', NULL, 2, 2),
(5, 'Dép dê lê trắng đen ', '499999', '299999', 'https://cf.shopee.vn/file/a1920c6d84340c1f718857488012eb6c', NULL, 1, 1),
(6, 'Apple Smart Keyboard Folio For iPad Pro ', '6999999', '5950000', 'https://cf.shopee.vn/file/fabe5d101de9ff01342f89a2f056aa12', NULL, 1, 2),
(7, 'Airpods2 Chính Hãng Repi ', '1250000', '950000', 'https://cf.shopee.vn/file/fb6dde613884de327e986c341d7b163a_tn', NULL, 1, 2),
(8, 'iPhone 13 12 11 Pro Max 7 8 6 6s Plus SE 2020 XS M... ', '23550000', '23550000', 'https://cf.shopee.vn/file/b7646d2ef4f1f9aab365eabbb57512d6', NULL, 1, 2),
(9, 'Apple Smart Keyboard Folio For iPad Pro', '5950000', '5950000', 'https://cf.shopee.vn/file/fabe5d101de9ff01342f89a2f056aa12', NULL, 1, 2),
(10, 'Note30 Plus nguyên bản 2022 màn hình lớn Video hỗ trợ trò chơi 5G Máy ảnh HD giảm', '1999000', '1250000', 'https://cf.shopee.vn/file/5eebc87c08a084fd557083eb4037a9e4_tn', NULL, 2, 2),
(11, 'Ốp Điện Thoại Chống Sốc Cho iPhone 12 Pro 12 Pro M... ', '120000', '120000', 'https://cf.shopee.vn/file/7a4dc9e7c0d39a14453b7579fe652c8d', NULL, 2, 16),
(12, 'Bộ Nồi Inox 5 Món STOCK POT Không Quai Đủ Kích Cỡ Siêu Tiện Lợi Đa Năng', '190000', '125000', 'https://cf.shopee.vn/file/9e421710e7fd8ca32d1449622b3bb3e7', NULL, 1, 3),
(13, 'Thẻ tag ', '275000', '255000', 'https://cf.shopee.vn/file/dbbf3af22dc1bfe6fbbfe8198a003fe0_tn', NULL, 2, 8),
(14, 'Mũ Bảo Hiểm Nửa Đầu Andes 139 Kính Âm An Toàn Gọn Nhẹ', '299000', '299000', 'https://cf.shopee.vn/file/376556663700ac58d0a464ce5d2a621e', NULL, 2, 1),
(15, 'Máy Cạo râu Khô YANDOU 301U - Máy cạo râu yandou', '456000', '399000', 'https://cf.shopee.vn/file/00275a88cc81d25f3be6a64596b45c0c', NULL, 2, 6),
(16, 'Kính cường lực chống nhìn trộm iphone full màn, đủ size 6/7/8/X/11/12/13/Plus/Max/Pro/Promax', '45000', '45000', 'https://cf.shopee.vn/file/31bd8b509e6746b5bc8b4072959f883a', NULL, 2, 6),
(17, 'Màn Hình hãng GLOWAY17|19|22|24 inch', '2455000', '2200000', 'https://cf.shopee.vn/file/9195d5e4ce67710b2f790e9f06634a12', NULL, 2, 2),
(18, 'GIÀY SỤC THỂ THAO ĐẾ CAO SU CAO CẤP', '490000', '389000', 'https://cf.shopee.vn/file/6c13bef8a6cf09936bab34a3be15b380', NULL, 3, 10),
(19, 'Apple MacBook Air (2020)', '27950000', '27950000', 'https://cf.shopee.vn/file/575e04c0e1d08b5f1b9f624a8d6b1419', NULL, 1, 2),
(20, 'Apple MacBook Air (2020)', '27950000', '27950000', 'https://cf.shopee.vn/file/575e04c0e1d08b5f1b9f624a8d6b1419', NULL, 1, 2),
(21, 'Apple MacBook Air (2020)', '27950000', '	27950000', 'https://cf.shopee.vn/file/575e04c0e1d08b5f1b9f624a8d6b1419', NULL, 1, 2),
(22, '	Bộ Nồi Inox 5 Món STOCK POT Không Quai Đủ Kích Cỡ ...', '5950000', '5950000', 'https://cf.shopee.vn/file/fabe5d101de9ff01342f89a2f056aa12', NULL, 1, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `shop`
--

CREATE TABLE `shop` (
  `shopID` int(11) NOT NULL,
  `user` varchar(50) NOT NULL,
  `pass` varchar(45) CHARACTER SET armscii8 COLLATE armscii8_general_ci NOT NULL,
  `nameShop` varchar(50) DEFAULT NULL,
  `numFollower` varchar(20) DEFAULT NULL,
  `urlAvatar` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `shop`
--

INSERT INTO `shop` (`shopID`, `user`, `pass`, `nameShop`, `numFollower`, `urlAvatar`) VALUES
(1, 'shop1', '123456', 'KALIKA', '14800', 'https://cf.shopee.vn/file/2b57567059da2ff42e6c11ec49b52306_tn'),
(2, 'shop2', '000000', 'MEMEO', '366666', 'https://cf.shopee.vn/file/a46098c1b5d42fbd59b548dac95d0d8a_tn'),
(3, 'shop3', '123456', 'MEMORY', '0', 'https://cf.shopee.vn/file/fc490b6a0ae80e1f3d3df8511cab4221_tn'),
(6, 'shop4', '123456', 'FREE_FIRE', '0', 'https://cf.shopee.vn/file/d283e74b9c35c84f7aff6009b6b186c9_tn');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `transaction`
--

CREATE TABLE `transaction` (
  `transactionID` int(11) NOT NULL,
  `shop_shopID` int(11) NOT NULL,
  `cilent_cilentID` int(11) NOT NULL,
  `product_productID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`cartID`,`client_clientID`,`product_productID`),
  ADD KEY `fk_cart_client_idx` (`client_clientID`),
  ADD KEY `fk_cart_product_idx` (`product_productID`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`categoryID`);

--
-- Chỉ mục cho bảng `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`clientID`);

--
-- Chỉ mục cho bảng `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`commentID`,`client_clientID`,`product_productID`),
  ADD KEY `fk_comment_silent1_idx` (`client_clientID`),
  ADD KEY `fk_comment_product_idx` (`product_productID`);

--
-- Chỉ mục cho bảng `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`paymentID`,`clientID`),
  ADD KEY `fk_payment_client_idx` (`clientID`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productID`,`shop_shopID`,`category_categoryID`),
  ADD KEY `fk_product_shop1_idx` (`shop_shopID`),
  ADD KEY `fk_product_category_idx` (`category_categoryID`);

--
-- Chỉ mục cho bảng `shop`
--
ALTER TABLE `shop`
  ADD PRIMARY KEY (`shopID`);

--
-- Chỉ mục cho bảng `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transactionID`,`shop_shopID`,`cilent_cilentID`,`product_productID`),
  ADD KEY `fk_transaction_shop_idx` (`shop_shopID`),
  ADD KEY `fk_transaction_silent1_idx` (`cilent_cilentID`),
  ADD KEY `fk_transaction_product1_idx` (`product_productID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `cart`
--
ALTER TABLE `cart`
  MODIFY `cartID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `categoryID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1002;

--
-- AUTO_INCREMENT cho bảng `client`
--
ALTER TABLE `client`
  MODIFY `clientID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `comment`
--
ALTER TABLE `comment`
  MODIFY `commentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `payment`
--
ALTER TABLE `payment`
  MODIFY `paymentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `productID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT cho bảng `shop`
--
ALTER TABLE `shop`
  MODIFY `shopID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transactionID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `fk_cart_client` FOREIGN KEY (`client_clientID`) REFERENCES `client` (`clientID`),
  ADD CONSTRAINT `fk_cart_product` FOREIGN KEY (`product_productID`) REFERENCES `product` (`productID`);

--
-- Các ràng buộc cho bảng `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `fk_comment_client1` FOREIGN KEY (`client_clientID`) REFERENCES `client` (`clientID`),
  ADD CONSTRAINT `fk_comment_product` FOREIGN KEY (`product_productID`) REFERENCES `product` (`productID`);

--
-- Các ràng buộc cho bảng `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `fk_payment_client` FOREIGN KEY (`clientID`) REFERENCES `client` (`clientID`);

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `fk_product_category` FOREIGN KEY (`category_categoryID`) REFERENCES `category` (`categoryID`),
  ADD CONSTRAINT `fk_product_shop1` FOREIGN KEY (`shop_shopID`) REFERENCES `shop` (`shopID`);

--
-- Các ràng buộc cho bảng `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `fk_transaction_cilent1` FOREIGN KEY (`cilent_cilentID`) REFERENCES `client` (`clientID`),
  ADD CONSTRAINT `fk_transaction_product1` FOREIGN KEY (`product_productID`) REFERENCES `product` (`productID`),
  ADD CONSTRAINT `fk_transaction_shop` FOREIGN KEY (`shop_shopID`) REFERENCES `shop` (`shopID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
