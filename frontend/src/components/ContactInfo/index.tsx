type ContactInfoProps = {
    icon: React.ReactNode;
    title: string;
    description: string;
}

export function ContactInfo({ icon, title, description }: ContactInfoProps) {
    return (
        <>
            <div className='flex items-center gap-4'>
              <div className='contactIcon glass p-4 border-border-glass rounded-xl'>
                {icon}
              </div>
              <div className='contactInfoDescription flex flex-col gap-2'>
                <h3 className='text-text-dim font-semibold text-xs'>{title}</h3>
                <p className='font-bold text-base'>{description}</p>
              </div>
            </div>
        </>
    );
}