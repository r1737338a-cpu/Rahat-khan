# kitti — Rahat's Voice Assistant (MVP)

এই কমিটে আমি প্রজেক্টের একটি MVP skeleton যোগ করেছি: MainActivity, ForegroundService (background microphone stub), প্রাথমিক permissions এবং একটি GitHub Actions workflow (debug APK build)।

প্রধান ফিচার (আপনি চেয়েছিলেন):
- Foreground service stub যা মাইক্রোফোন access করে (wake-word ইন্টিগ্রেশন বাকি)
- প্রয়োজনীয় permissions ম্যানেজ করা হবে
- build workflow (debug) যোগ করা হয়েছে (নোট: gradle wrapper না থাকলে CI বিল্ড ফেল হতে পারে; আমি পরবর্তী কমিটে wrapper যোগ করব)
