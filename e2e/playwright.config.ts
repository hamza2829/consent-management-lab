import { defineConfig } from '@playwright/test';
export default defineConfig({ testDir: './tests', use: { baseURL: process.env.PORTAL_URL || 'http://127.0.0.1:4200', headless: true } });
