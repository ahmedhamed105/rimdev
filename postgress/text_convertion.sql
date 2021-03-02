CREATE TABLE IF NOT EXISTS text_convertion (
  ID serial primary key,
  return_lang VARCHAR(1000) NOT NULL,
  Languages_ID INT NOT NULL,
  language_map_ID INT NOT NULL,
  date_modify timestamp NOT NULL,
  date_create timestamp NOT NULL);
  
  INSERT INTO text_convertion (ID, return_lang, Languages_ID, language_map_ID, date_modify, date_create) VALUES 
 (1, 'dashboard', 1, 1, '2020-03-21 11:47:10', '2020-03-21 11:47:10')
,(2, 'لوحة القيادة', 2, 1, '2020-03-21 11:47:10', '2020-03-21 11:47:10')
,(3, 'tableau de bord', 3, 1, '2020-03-21 11:47:10', '2020-03-21 11:47:10')
,(4, 'admin', 1, 2, '2020-03-21 11:48:36', '2020-03-21 11:48:36')
,(5, 'مشرف', 2, 2, '2020-03-21 11:48:36', '2020-03-21 11:48:36')
,(6, 'administrateur', 3, 2, '2020-03-21 11:48:36', '2020-03-21 11:48:36')
,(7, 'financial', 1, 3, '2020-03-21 11:51:24', '2020-03-21 11:51:24')
,(8, 'الأمور المالية', 2, 3, '2020-03-21 11:51:24', '2020-03-21 11:51:24')
,(9, 'financier', 3, 3, '2020-03-21 11:51:24', '2020-03-21 11:51:24')
,(10, 'language', 1, 4, '2020-03-21 11:51:50', '2020-03-21 11:51:50')
,(11, 'لغة', 2, 4, '2020-03-21 11:51:51', '2020-03-21 11:51:51')
,(12, 'Langue', 3, 4, '2020-03-21 11:51:51', '2020-03-21 11:51:51')
,(13, 'Devices', 1, 5, '2020-03-21 13:12:44', '2020-03-21 13:12:44')
,(14, 'الأجهزة', 2, 5, '2020-03-21 13:12:44', '2020-03-21 13:12:44')
,(15, 'Dispositifs', 3, 5, '2020-03-21 13:12:44', '2020-03-21 13:12:44')
,(16, 'Users', 1, 6, '2020-03-21 13:13:17', '2020-03-21 13:13:17')
,(17, 'المستخدمون', 2, 6, '2020-03-21 13:13:17', '2020-03-21 13:13:17')
,(18, 'Utilisateurs', 3, 6, '2020-03-21 13:13:17', '2020-03-21 13:13:17')
,(19, 'email', 1, 7, '2020-03-21 13:13:36', '2020-03-21 13:13:36')
,(20, 'البريد الإلكتروني', 2, 7, '2020-03-21 13:13:36', '2020-03-21 13:13:36')
,(21, 'email', 3, 7, '2020-03-21 13:13:36', '2020-03-21 13:13:36')
,(22, 'telephone', 1, 8, '2020-03-21 13:13:58', '2020-03-21 13:13:58')
,(23, 'هاتف', 2, 8, '2020-03-21 13:13:58', '2020-03-21 13:13:58')
,(24, 'Téléphone', 3, 8, '2020-03-21 13:13:59', '2020-03-21 13:13:59')
,(25, 'pages', 1, 9, '2020-03-21 13:14:25', '2020-03-21 13:14:25')
,(26, 'الصفحات', 2, 9, '2020-03-21 13:14:25', '2020-03-21 13:14:25')
,(27, 'pages', 3, 9, '2020-03-21 13:14:25', '2020-03-21 13:14:25')
,(28, 'Currency', 1, 10, '2020-03-21 13:15:14', '2020-03-21 13:15:14')
,(29, 'عملة', 2, 10, '2020-03-21 13:15:14', '2020-03-21 13:15:14')
,(30, 'Devise', 3, 10, '2020-03-21 13:15:14', '2020-03-21 13:15:14')
,(31, 'Flow type', 1, 11, '2020-03-21 13:15:40', '2020-03-21 13:15:40')
,(32, 'نوع التدفق', 2, 11, '2020-03-21 13:15:40', '2020-03-21 13:15:40')
,(33, 'Type d''écoulement', 3, 11, '2020-03-21 13:15:40', '2020-03-21 13:15:40')
,(34, 'language text', 1, 12, '2020-03-21 13:16:10', '2020-03-21 13:16:10')
,(35, 'نص اللغة', 2, 12, '2020-03-21 13:16:10', '2020-03-21 13:16:10')
,(36, 'texte de langue', 3, 12, '2020-03-21 13:16:10', '2020-03-21 13:16:10')
,(37, 'language Codes', 1, 13, '2020-03-21 13:16:37', '2020-03-21 13:16:37')
,(38, 'رموز اللغة', 2, 13, '2020-03-21 13:16:37', '2020-03-21 13:16:37')
,(39, 'Codes de langue', 3, 13, '2020-03-21 13:16:37', '2020-03-21 13:16:37')
,(46, 'Profile image', 1, 14, '2020-03-21 20:33:53', '2020-03-21 20:33:53')
,(47, 'صورة الملف الشخصي', 2, 14, '2020-03-21 20:33:53', '2020-03-21 20:33:53')
,(48, 'Image de profil', 3, 14, '2020-03-21 20:33:53', '2020-03-21 20:33:53')
,(49, 'First name', 1, 15, '2020-03-21 20:35:24', '2020-03-21 20:35:24')
,(50, 'الاسم الاول', 2, 15, '2020-03-21 20:35:24', '2020-03-21 20:35:24')
,(51, 'Prénom', 3, 15, '2020-03-21 20:35:24', '2020-03-21 20:35:24')
,(52, 'Last name', 1, 16, '2020-03-21 20:36:08', '2020-03-21 20:36:08')
,(53, 'اسم العائلة', 2, 16, '2020-03-21 20:36:08', '2020-03-21 20:36:08')
,(54, 'Nom de famille', 3, 16, '2020-03-21 20:36:08', '2020-03-21 20:36:08')
,(55, 'birthday', 1, 17, '2020-03-21 20:36:45', '2020-03-21 20:36:45')
,(56, 'عيد الميلاد', 2, 17, '2020-03-21 20:36:45', '2020-03-21 20:36:45')
,(57, 'anniversaire', 3, 17, '2020-03-21 20:36:46', '2020-03-21 20:36:46')
,(58, 'ID number', 1, 18, '2020-03-21 20:44:48', '2020-03-21 20:44:48')
,(59, 'رقم الهوية', 2, 18, '2020-03-21 20:44:48', '2020-03-21 20:44:48')
,(60, 'Numéro d''identification', 3, 18, '2020-03-21 20:44:48', '2020-03-21 20:44:48')
,(61, 'ID number image', 1, 19, '2020-03-21 20:45:17', '2020-03-21 20:45:17')
,(62, 'صورة رقم الهوية', 2, 19, '2020-03-21 20:45:17', '2020-03-21 20:45:17')
,(63, 'Image du numéro d''identification', 3, 19, '2020-03-21 20:45:17', '2020-03-21 20:45:17')
,(64, 'username', 1, 20, '2020-03-21 20:45:53', '2020-03-21 20:45:53')
,(65, 'اسم المستخدم', 2, 20, '2020-03-21 20:45:53', '2020-03-21 20:45:53')
,(66, 'Nom d''utilisateur', 3, 20, '2020-03-21 20:45:53', '2020-03-21 20:45:53')
,(67, 'password', 1, 21, '2020-03-21 20:46:28', '2020-03-21 20:46:28')
,(68, 'كلمه السر', 2, 21, '2020-03-21 20:46:28', '2020-03-21 20:46:28')
,(69, 'mot de passe', 3, 21, '2020-03-21 20:46:28', '2020-03-21 20:46:28')
,(70, 'Notes', 1, 22, '2020-03-21 20:46:58', '2020-03-21 20:46:58')
,(71, 'ملاحظات', 2, 22, '2020-03-21 20:46:58', '2020-03-21 20:46:58')
,(72, 'Remarques', 3, 22, '2020-03-21 20:46:58', '2020-03-21 20:46:58')
,(73, 'Application type', 1, 23, '2020-03-21 20:47:20', '2020-03-21 20:47:20')
,(74, 'نوع التطبيق', 2, 23, '2020-03-21 20:47:20', '2020-03-21 20:47:20')
,(75, 'Type d''application', 3, 23, '2020-03-21 20:47:20', '2020-03-21 20:47:20')
,(76, 'type', 1, 24, '2020-03-21 20:48:07', '2020-03-21 20:48:07')
,(77, 'النوع', 2, 24, '2020-03-21 20:48:07', '2020-03-21 20:48:07')
,(78, 'type', 3, 24, '2020-03-21 20:48:07', '2020-03-21 20:48:07')
,(79, 'privilege Group', 1, 25, '2020-03-21 20:48:30', '2020-03-21 20:48:30')
,(80, 'مجموعة الامتياز', 2, 25, '2020-03-21 20:48:30', '2020-03-21 20:48:30')
,(81, 'groupe privilège', 3, 25, '2020-03-21 20:48:30', '2020-03-21 20:48:30')
,(82, 'status', 1, 26, '2020-03-21 20:49:08', '2020-03-21 20:49:08')
,(83, 'الحالة', 2, 26, '2020-03-21 20:49:08', '2020-03-21 20:49:08')
,(84, 'statut', 3, 26, '2020-03-21 20:49:09', '2020-03-21 20:49:09')
,(85, 'submit', 1, 27, '2020-03-21 20:49:34', '2020-03-21 20:49:34')
,(86, 'إرسال', 2, 27, '2020-03-21 20:49:34', '2020-03-21 20:49:34')
,(87, 'soumettre', 3, 27, '2020-03-21 20:49:34', '2020-03-21 20:49:34')
,(88, 'reset', 1, 28, '2020-03-21 20:50:26', '2020-03-21 20:50:26')
,(89, 'إعادة تعيين', 2, 28, '2020-03-21 20:50:26', '2020-03-21 20:50:26')
,(90, 'réinitialiser', 3, 28, '2020-03-21 20:50:26', '2020-03-21 20:50:26')
,(91, 'inserting form', 1, 29, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(92, 'إدخال نموذج', 2, 29, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(93, 'insertion d''un formulaire', 3, 29, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(94, 'login', 1, 30, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(95, 'تسجيل الدخول', 2, 30, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(96, 's''identifier', 3, 30, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(97, '{{field}} is required', 1, 31, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(98, '{{field}} مطلوب', 2, 31, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(99, '{{field}} est requis', 3, 31, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(100, '{{data}} is wrong', 1, 32, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(101, '{{data}} خطأ', 2, 32, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(102, '{{data}} est faux', 3, 32, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(103, 'please enter {{filecount}} file/files only', 1, 33, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(104, ' ملف / ملفات فقط{{filecount}} يرجى إدخال  ', 2, 33, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(105, 'veuillez saisir {{filecount}} fichier / fichiers uniquement', 3, 33, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(106, 'size is larger than {{maxfilesize}}', 1, 34, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(107, ' {{maxfilesize}} حجم أكبر من', 2, 34, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(108, 'la taille est plus grande que {{maxfilesize}}', 3, 34, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(109, 'file type error {{wrongtype}}', 1, 35, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(110, ' {{wrongtype}} خطأ في نوع الملف', 2, 35, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(111, 'erreur de type de fichier {{wrongtype}}', 3, 35, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(112, 'update', 1, 36, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(113, 'تحديث', 2, 36, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(114, 'mise à jour', 3, 36, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(115, 'Display', 1, 37, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(116, 'عرض', 2, 37, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(117, 'Afficher', 3, 37, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(118, 'Delete', 1, 38, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(119, 'حذف', 2, 38, '2020-03-21 20:50:47', '2020-03-21 20:50:47')
,(120, 'Supprimer', 3, 38, '2020-03-21 20:50:47', '2020-03-21 20:50:47');