import { Stack } from 'expo-router/stack';
import React from 'react';

export default function Layout() {
  return (
    <Stack>
      <Stack.Screen name="(tabs)" options={{ 
        headerShown: false,
        contentStyle: { backgroundColor: 'transparent' },
      }} />
      <Stack.Screen name="userProfile/index" options={{
        headerShown: false,
        contentStyle: { backgroundColor: 'transparent' },
      }} />
      <Stack.Screen name="notifications/index" options={{
        headerShown: false,
        contentStyle: { backgroundColor: 'transparent' },
      }} />
      <Stack.Screen name="movie/[id]" options={{
        headerShown: false,
        contentStyle: { backgroundColor: 'transparent' },
      }} />
    </Stack>
  );
}