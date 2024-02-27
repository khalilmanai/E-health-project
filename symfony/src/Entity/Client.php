<?php

namespace App\Entity;

use App\Repository\ClientRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: ClientRepository::class)]

class Client extends User
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]

    public function __construct()
    {
    }
}
