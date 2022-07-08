
INSERT INTO public.tenants (name) VALUES ('tenant_1') ON CONFLICT DO NOTHING;
INSERT INTO public.tenants (name) VALUES ('tenant_2')  ON CONFLICT DO NOTHING;

------------------------------------------------------------------------------------------
INSERT INTO public.users (tenant_id, email, name)
  VALUES
((SELECT id FROM public.tenants WHERE name = 'tenant_1'), 'tenant_1+user1@gmail.com', 'Tenant 1 User 1') ON CONFLICT DO NOTHING;

INSERT INTO public.users (tenant_id, email, name)
  VALUES
((SELECT id FROM public.tenants WHERE name = 'tenant_1'), 'tenant_1+user2@gmail.com', 'Tenant 1 User 2') ON CONFLICT DO NOTHING;

INSERT INTO public.users (tenant_id, email, name)
  VALUES
((SELECT id FROM public.tenants WHERE name = 'tenant_2'), 'tenant_2+user1@gmail.com', 'Tenant 2 User 1') ON CONFLICT DO NOTHING;

INSERT INTO public.users (tenant_id, email, name)
  VALUES
((SELECT id FROM public.tenants WHERE name = 'tenant_2'), 'tenant_2+user2@gmail.com', 'Tenant 2 User 2') ON CONFLICT DO NOTHING;


