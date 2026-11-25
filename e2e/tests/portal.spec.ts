import { test, expect } from '@playwright/test';
test('customer can review consent purposes', async ({ page }) => {
  await page.goto('/');
  await expect(page.getByRole('heading', { name: 'Your consent, your choice.' })).toBeVisible();
  await expect(page.getByText('Marketing communications')).toBeVisible();
});
test('customer can toggle a purpose', async ({ page }) => {
  await page.goto('/');
  const toggle = page.getByRole('checkbox', { name: 'Marketing communications' });
  await toggle.check();
  await expect(toggle).toBeChecked();
});
